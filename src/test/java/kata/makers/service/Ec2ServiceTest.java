package kata.makers.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import service.Ec2Service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class Ec2ServiceTest {

  private Ec2Service testEc2Service;
  private AmazonEC2 mockEC2Client;
  private MockedStatic<AmazonEC2ClientBuilder> mockedStaticBuilder;

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  void setUp() {
    testEc2Service = new Ec2Service();

    System.setOut(new PrintStream(outContent));

    mockEC2Client = mock(AmazonEC2.class);
    AmazonEC2ClientBuilder mockBuilder = mock(AmazonEC2ClientBuilder.class);

    mockedStaticBuilder = mockStatic(AmazonEC2ClientBuilder.class);
    mockedStaticBuilder.when(AmazonEC2ClientBuilder::standard).thenReturn(mockBuilder);
    when(mockBuilder.withRegion(Regions.EU_WEST_1)).thenReturn(mockBuilder);
    when(mockBuilder.build()).thenReturn(mockEC2Client);
  }

  @AfterEach
  void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  void someOfTheServicesInComponentIsNotStarted() {
    Reservation reservation = new Reservation().withInstances(
        createMockInstance("orrx", "order-receiver"));
    DescribeInstancesResult describeInstancesResult = new DescribeInstancesResult()
        .withReservations(reservation);

    when(mockEC2Client.describeInstances(any(DescribeInstancesRequest.class)))
        .thenReturn(describeInstancesResult);

    testEc2Service.listRunningInstance();

    assertTrue(outContent.toString().contains("Orders: [order_management, billing]"));
  }

  @Test
  void allServicesInAComponentStarted() {
    Reservation reservation = new Reservation().withInstances(getOrderManagementComponent());
    DescribeInstancesResult describeInstancesResult = new DescribeInstancesResult()
        .withReservations(reservation);

    when(mockEC2Client.describeInstances(any(DescribeInstancesRequest.class)))
        .thenReturn(describeInstancesResult);

    testEc2Service.listRunningInstance();

    assertTrue(outContent.toString().contains("Orders: [billing]"));
  }

  @Test
  void allServicesRequireByOrdersRegressionTestHasBeenStarted() {
    Reservation reservation = new Reservation().withInstances(
        Stream.concat(getOrderManagementComponent().stream(), getBillingComponent().stream()).toList()
    );
    DescribeInstancesResult describeInstancesResult = new DescribeInstancesResult()
        .withReservations(reservation);

    when(mockEC2Client.describeInstances(any(DescribeInstancesRequest.class)))
        .thenReturn(describeInstancesResult);

    testEc2Service.listRunningInstance();

    assertTrue(outContent.toString().contains("Orders: []"));
  }

  private Instance createMockInstance(String serviceShortName, String serviceFullName) {
    return new Instance()
        .withInstanceId(UUID.randomUUID().toString())
        .withTags(
            new Tag("Role", serviceShortName),
            new Tag("Environment", "dev"),
            new Tag("MicroService", serviceFullName)
        );
  }

  private List<Instance> getOrderManagementComponent() {
    return List.of(
        createMockInstance("orrx", "order-receiver"),
        createMockInstance("orcx", "order-converter"),
        createMockInstance("oisu", "order-invoice-status-updater"));
  }

  private List<Instance> getBillingComponent() {
    return List.of(
        createMockInstance("trds", "transaction-details-sender"),
        createMockInstance("trsb", "transaction-snapshot-builder"),
        createMockInstance("trdr", "transaction-details-receiver"),
        createMockInstance("inas", "invoice-amendment-sender"),
        createMockInstance("cats", "calculate-tax-sender"),
        createMockInstance("atss", "audited-tax-sender"),
        createMockInstance("trsr", "transaction-status-receiver"),
        createMockInstance("insr", "invoice-status-router"),
        createMockInstance("insp", "invoice-status-producer"),
        createMockInstance("cnsp", "credit-note-status-producer"));

  }
}