package test.tally.report.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import test.tally.report.KibanaService;

@Slf4j
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class BulkQuery {

    private List<String> kibanaResponses = new ArrayList<>();
    private List<String> kibanaResponse;
    private final KibanaService kibanaClient = new KibanaService();

    private String RECORD_IDENTIFIER;
    private String RECORD_TYPE;
    private String SOURCE_SYSTEM;
    private String SOURCE_REFERENCE_NUMBER;
    private String CONTRACT_REFERENCE;
    private String ACCOUNTING_MODEL;
    private String BILLING_ENTITY;
    private String CUSTOMER_REFERENCE;
    private String CUSTOMER_NAME;
    private String CURRENCY_CODE;
    private String LINE_NUMBER;
    private String TARGET_TRX_NUMBER;
    private String TRX_REFERENCE;
    private String LINE_REFERENCE;
    private String TARGET_TRX_LINE_NUMBER;
    private String ORIGINAL_RECORD_IDENTIFIER;
    private String SOURCE_LINE_REFERENCE;
    private String PRODUCT_CODE;
    private String JOURNAL_NUMBER;
    private String ISBN;
    private String TRX_DATE;
    private String TRX_FULFILMENT_DATE;
    private String NET_AMOUNT;
    private String GROSS_AMOUNT;
    private String IMPLIED_DISCOUNT;
    private String LINE_DESCRIPTION;
    private String SUBSCRIPTION_YEAR;
    private String VOLUME_ISSUE_NUMBER;
    private String REASON_CODE;
    private String DISCOUNT_RATE;

    private List<String> RECORD_IDENTIFIER_LIST = new ArrayList<>();
    private List<String> RECORD_TYPE_LIST  = new ArrayList<>();
    private List<String> SOURCE_SYSTEM_LIST  = new ArrayList<>();
    private List<String> SOURCE_REFERENCE_NUMBER_LIST  = new ArrayList<>();
    private List<String> CONTRACT_REFERENCE_LIST  = new ArrayList<>();
    private List<String> ACCOUNTING_MODEL_LIST  = new ArrayList<>();
    private List<String> BILLING_ENTITY_LIST  = new ArrayList<>();
    private List<String> CUSTOMER_REFERENCE_LIST  = new ArrayList<>();
    private List<String> CUSTOMER_NAME_LIST  = new ArrayList<>();
    private List<String> CURRENCY_CODE_LIST  = new ArrayList<>();
    private List<String> LINE_NUMBER_LIST  = new ArrayList<>();
    private List<String> TARGET_TRX_NUMBER_LIST  = new ArrayList<>();
    private List<String> TRX_REFERENCE_LIST  = new ArrayList<>();
    private List<String> LINE_REFERENCE_LIST  = new ArrayList<>();
    private List<String> TARGET_TRX_LINE_NUMBER_LIST  = new ArrayList<>();
    private List<String> ORIGINAL_RECORD_IDENTIFIER_LIST  = new ArrayList<>();
    private List<String> SOURCE_LINE_REFERENCE_LIST  = new ArrayList<>();
    private List<String> PRODUCT_CODE_LIST  = new ArrayList<>();
    private List<String> JOURNAL_NUMBER_LIST  = new ArrayList<>();
    private List<String> ISBN_LIST  = new ArrayList<>();
    private List<String> TRX_DATE_LIST = new ArrayList<>();
    private List<String> TRX_FULFILMENT_DATE_LIST  = new ArrayList<>();
    private List<String> NET_AMOUNT_LIST  = new ArrayList<>();
    private List<String> GROSS_AMOUNT_LIST  = new ArrayList<>();
    private List<String> IMPLIED_DISCOUNT_LIST  = new ArrayList<>();
    private List<String> LINE_DESCRIPTION_LIST  = new ArrayList<>();
    private List<String> SUBSCRIPTION_YEAR_LIST  = new ArrayList<>();
    private List<String> VOLUME_ISSUE_NUMBER_LIST  = new ArrayList<>();
    private List<String> REASON_CODE_LIST  = new ArrayList<>();
    private List<String> DISCOUNT_RATE_LIST  = new ArrayList<>();



    @When("requested to search for bulk transactions,")
    public void requestedToSearchForBulkTransactions() {
        List<String> GLRevRec = List.of("REV01034904OA2-1","REV01213573OA2-1","REV01213549OA2-1","REV01186090OA2-1","REV01213350OA2-1","REV01208871OA2-1","REV01213314OA2-1","REV01201259OA2-1","REV01213372OA2-1","REV01209706OA2-1","REV01212540OA2-1","REV01212996OA2-1","REV01132616OA2-1","REV01215817OA2-1","REV01215832OA2-1","REV01117674OA2-1","REV01132400OA2-1","REV01147430OA2-1","REV01151094OA2-1","REV01133275OA2-1","REV01128338OA2-1","REV01167640OA2-1","REV01132289OA2-1","REV01215841OA2-1","REV01215859OA2-1","REV01215860OA2-1","REV01215864OA2-1","REV01215871OA2-1","REV01215877OA2-1","REV01215892OA2-1","REV01215893OA2-1","REV01215894OA2-1","REV01215902OA2-1","REV01215905OA2-1","REV01215908OA2-1","REV01215914OA2-1","REV01194457OA2-1","REV01211878OA2-1","REV01215941OA2-1","REV01107728OA2-1","REV01215967OA2-1","REV01215982OA2-1","REV01215999OA2-1","REV01209224OA2-1","REV01216005OA2-1","REV01216006OA2-1","REV01211032OA2-1","REV01213172OA2-1","REV01209047OA2-1","REV01216019OA2-1","REV01213813OA2-1","REV01066427OA2-1","REV01158473OA2-1","REV01176175OA2-1","REV01202802OA2-1","REV01170470OA2-1","REV01216026OA2-1","REV01126903OA2-1","REV01216036OA2-1","REV01209486OA2-1","REV01206527OA2-1","REV01202339OA2-1","REV01206109OA2-1","REV01216072OA2-1");
        for(String revRec : GLRevRec) {
            log.info("revRec = {}", revRec);
            kibanaResponse = kibanaClient.getTestData("\"REQ_OUT\" AND \"SF_MQ2COM_" + revRec + "\"", "revenue-recognition-sender-v3", "2024-10-04T15:36:56.694Z",
                "2024-12-02T17:36:56.694Z", "Payload: ");
            log.info("kibanaResponse = {}", kibanaResponse.get(0));

            RECORD_IDENTIFIER = extractTagValue(kibanaResponse.get(0), "RECORD_IDENTIFIER");
            RECORD_IDENTIFIER_LIST.add(RECORD_IDENTIFIER);

            RECORD_TYPE = extractTagValue(kibanaResponse.get(0), "RECORD_TYPE");
            RECORD_TYPE_LIST.add(RECORD_TYPE);

            SOURCE_SYSTEM = extractTagValue(kibanaResponse.get(0), "SOURCE_SYSTEM");
            SOURCE_SYSTEM_LIST.add(SOURCE_SYSTEM);

            SOURCE_REFERENCE_NUMBER = extractTagValue(kibanaResponse.get(0), "SOURCE_REFERENCE_NUMBER");
            SOURCE_REFERENCE_NUMBER_LIST.add(SOURCE_REFERENCE_NUMBER);

            CONTRACT_REFERENCE = extractTagValue(kibanaResponse.get(0), "CONTRACT_REFERENCE");
            CONTRACT_REFERENCE_LIST.add(CONTRACT_REFERENCE);

            ACCOUNTING_MODEL = extractTagValue(kibanaResponse.get(0), "ACCOUNTING_MODEL");
            ACCOUNTING_MODEL_LIST.add(ACCOUNTING_MODEL);

            BILLING_ENTITY = extractTagValue(kibanaResponse.get(0), "BILLING_ENTITY");
            BILLING_ENTITY_LIST.add(BILLING_ENTITY);

            CUSTOMER_REFERENCE = extractTagValue(kibanaResponse.get(0), "CUSTOMER_REFERENCE");
            CUSTOMER_REFERENCE_LIST.add(CUSTOMER_REFERENCE);

            CUSTOMER_NAME = extractTagValue(kibanaResponse.get(0), "CUSTOMER_NAME");
            CUSTOMER_NAME_LIST.add(CUSTOMER_NAME);

            CURRENCY_CODE = extractTagValue(kibanaResponse.get(0), "CURRENCY_CODE");
            CURRENCY_CODE_LIST.add(CURRENCY_CODE);

            RECORD_TYPE = extractTagValue(kibanaResponse.get(0), "RECORD_TYPE");
            RECORD_TYPE_LIST.add(RECORD_TYPE);

            SOURCE_SYSTEM = extractTagValue(kibanaResponse.get(0), "SOURCE_SYSTEM");
            SOURCE_SYSTEM_LIST.add(SOURCE_SYSTEM);

            SOURCE_REFERENCE_NUMBER = extractTagValue(kibanaResponse.get(0), "SOURCE_REFERENCE_NUMBER");
            SOURCE_REFERENCE_NUMBER_LIST.add(SOURCE_REFERENCE_NUMBER);

            CONTRACT_REFERENCE = extractTagValue(kibanaResponse.get(0), "CONTRACT_REFERENCE");
            CONTRACT_REFERENCE_LIST.add(CONTRACT_REFERENCE);

            ACCOUNTING_MODEL= extractTagValue(kibanaResponse.get(0), "ACCOUNTING_MODEL");
            ACCOUNTING_MODEL_LIST.add(ACCOUNTING_MODEL);

            BILLING_ENTITY = extractTagValue(kibanaResponse.get(0), "BILLING_ENTITY");
            BILLING_ENTITY_LIST.add(BILLING_ENTITY);

            CUSTOMER_REFERENCE = extractTagValue(kibanaResponse.get(0), "CUSTOMER_REFERENCE");
            CUSTOMER_REFERENCE_LIST.add(CUSTOMER_REFERENCE);

            CUSTOMER_NAME = extractTagValue(kibanaResponse.get(0), "CUSTOMER_NAME");
            CUSTOMER_NAME_LIST.add(CUSTOMER_NAME);

            CURRENCY_CODE = extractTagValue(kibanaResponse.get(0), "CURRENCY_CODE");
            CURRENCY_CODE_LIST.add(CURRENCY_CODE);

            LINE_NUMBER = extractTagValue(kibanaResponse.get(0), "LINE_NUMBER");
            LINE_NUMBER_LIST.add(LINE_NUMBER);

            TARGET_TRX_NUMBER = extractTagValue(kibanaResponse.get(0), "TARGET_TRX_NUMBER");
            TARGET_TRX_NUMBER_LIST.add(TARGET_TRX_NUMBER);

            TRX_REFERENCE = extractTagValue(kibanaResponse.get(0), "TRX_REFERENCE");
            TRX_REFERENCE_LIST.add(TRX_REFERENCE);

            LINE_REFERENCE = extractTagValue(kibanaResponse.get(0), "LINE_REFERENCE");
            LINE_REFERENCE_LIST.add(LINE_REFERENCE);

            TARGET_TRX_LINE_NUMBER = extractTagValue(kibanaResponse.get(0), "TARGET_TRX_LINE_NUMBER");
            TARGET_TRX_LINE_NUMBER_LIST.add(TARGET_TRX_LINE_NUMBER);

            ORIGINAL_RECORD_IDENTIFIER = extractTagValue(kibanaResponse.get(0), "ORIGINAL_RECORD_IDENTIFIER");
            ORIGINAL_RECORD_IDENTIFIER_LIST.add(ORIGINAL_RECORD_IDENTIFIER);

            SOURCE_LINE_REFERENCE = extractTagValue(kibanaResponse.get(0), "SOURCE_LINE_REFERENCE");
            SOURCE_LINE_REFERENCE_LIST.add(SOURCE_LINE_REFERENCE);

            PRODUCT_CODE = extractTagValue(kibanaResponse.get(0), "PRODUCT_CODE");
            PRODUCT_CODE_LIST.add(PRODUCT_CODE);

            JOURNAL_NUMBER = extractTagValue(kibanaResponse.get(0), "JOURNAL_NUMBER");
            JOURNAL_NUMBER_LIST.add(JOURNAL_NUMBER);

            ISBN = extractTagValue(kibanaResponse.get(0), "ISBN");
            ISBN_LIST.add(ISBN);

            TRX_DATE = extractTagValue(kibanaResponse.get(0), "TRX_DATE");
            TRX_DATE_LIST.add(TRX_DATE);

            TRX_FULFILMENT_DATE = extractTagValue(kibanaResponse.get(0), "TRX_FULFILMENT_DATE");
            TRX_FULFILMENT_DATE_LIST.add(TRX_FULFILMENT_DATE);

            NET_AMOUNT = extractTagValue(kibanaResponse.get(0), "NET_AMOUNT");
            NET_AMOUNT_LIST.add(NET_AMOUNT);

            GROSS_AMOUNT = extractTagValue(kibanaResponse.get(0), "GROSS_AMOUNT");
            GROSS_AMOUNT_LIST.add(GROSS_AMOUNT);

            IMPLIED_DISCOUNT = extractTagValue(kibanaResponse.get(0), "IMPLIED_DISCOUNT");
            IMPLIED_DISCOUNT_LIST.add(IMPLIED_DISCOUNT);

            LINE_DESCRIPTION = extractTagValue(kibanaResponse.get(0), "LINE_DESCRIPTION");
            LINE_DESCRIPTION_LIST.add(LINE_DESCRIPTION);

            SUBSCRIPTION_YEAR = extractTagValue(kibanaResponse.get(0), "SUBSCRIPTION_YEAR");
            SUBSCRIPTION_YEAR_LIST.add(SUBSCRIPTION_YEAR);

            VOLUME_ISSUE_NUMBER = extractTagValue(kibanaResponse.get(0), "VOLUME_ISSUE_NUMBER");
            VOLUME_ISSUE_NUMBER_LIST.add(VOLUME_ISSUE_NUMBER);

            REASON_CODE = extractTagValue(kibanaResponse.get(0), "REASON_CODE");
            REASON_CODE_LIST.add(REASON_CODE);

            DISCOUNT_RATE = extractTagValue(kibanaResponse.get(0), "DISCOUNT_RATE");
            DISCOUNT_RATE_LIST.add(DISCOUNT_RATE);

        }
//        kibanaResponses = kibanaClient.getTestData("\"REQ_OUT\" AND \"SF_MQ2COM_REV01228243OA2-1\"", "revenue-recognition-sender-v3", "2024-10-04T15:36:56.694Z",
//            "2024-12-02T17:36:56.694Z", "Payload: ");
//        log.info("kibanaResponses = {}", kibanaResponses);
    }

    @Then("retrieve the necessary data")
    public void retrieveTheNecessaryData() {
//        for(String kibanaResponse : kibanaResponses) {
//            JSONObject kibanaResponseJson = new JSONObject(kibanaResponse);
//                contractReference = extractTagValue(kibanaResponse, "ns2:CONTRACT_REFERENCE");
//            this.contractReferences.add(contractReference);
//        }
    }

    @And("create a table to organize the information.")
    public void createATableToOrganizeTheInformation() {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("1 RECORD_IDENTIFIER_LIST = {}", RECORD_IDENTIFIER_LIST);
        log.info("2 RECORD_TYPE_LIST = {}", RECORD_TYPE_LIST);
        log.info("3 SOURCE_SYSTEM_LIST = {}", SOURCE_SYSTEM_LIST);
        log.info("4 SOURCE_REFERENCE_NUMBER_LIST = {}", SOURCE_REFERENCE_NUMBER_LIST);
        log.info("5 CONTRACT_REFERENCE_LIST = {}", CONTRACT_REFERENCE_LIST);
        log.info("6 ACCOUNTING_MODEL_LIST = {}", ACCOUNTING_MODEL_LIST);
        log.info("7 BILLING_ENTITY_LIST = {}", BILLING_ENTITY_LIST);
        log.info("8 CUSTOMER_REFERENCE_LIST = {}", CUSTOMER_REFERENCE_LIST);
        log.info("9 CUSTOMER_NAME_LIST = {}", CUSTOMER_NAME_LIST);
        log.info("10 CURRENCY_CODE_LIST = {}", CURRENCY_CODE_LIST);
        log.info("11 LINE_NUMBER_LIST = {}", LINE_NUMBER_LIST);
        log.info("12 TARGET_TRX_NUMBER_LIST = {}", TARGET_TRX_NUMBER_LIST);
        log.info("13 TRX_REFERENCE_LIST = {}", TRX_REFERENCE_LIST);
        log.info("14 LINE_REFERENCE_LIST = {}", LINE_REFERENCE_LIST);
        log.info("15 TARGET_TRX_LINE_NUMBER_LIST = {}", TARGET_TRX_LINE_NUMBER_LIST);
        log.info("16 ORIGINAL_RECORD_IDENTIFIER_LIST = {}", ORIGINAL_RECORD_IDENTIFIER_LIST);
        log.info("17 SOURCE_LINE_REFERENCE_LIST = {}", SOURCE_LINE_REFERENCE_LIST);
        log.info("18 PRODUCT_CODE_LIST = {}", PRODUCT_CODE_LIST);
        log.info("19 JOURNAL_NUMBER_LIST = {}", JOURNAL_NUMBER_LIST);
        log.info("20 ISBN_LIST = {}", ISBN_LIST);
        log.info("21 TRX_DATE_LIST = {}", TRX_DATE_LIST);
        log.info("22 TRX_FULFILMENT_DATE_LIST = {}", TRX_FULFILMENT_DATE_LIST);
        log.info("23 NET_AMOUNT_LIST = {}", NET_AMOUNT_LIST);
        log.info("24 GROSS_AMOUNT_LIST = {}", GROSS_AMOUNT_LIST);
        log.info("25 IMPLIED_DISCOUNT_LIST = {}", IMPLIED_DISCOUNT_LIST);
        log.info("26 LINE_DESCRIPTION_LIST = {}", LINE_DESCRIPTION_LIST);
        log.info("27 SUBSCRIPTION_YEAR_LIST = {}", SUBSCRIPTION_YEAR_LIST);
        log.info("28 VOLUME_ISSUE_NUMBER_LIST = {}", VOLUME_ISSUE_NUMBER_LIST);
        log.info("29 REASON_CODE_LIST = {}", REASON_CODE_LIST);
        log.info("30 DISCOUNT_RATE_LIST = {}", DISCOUNT_RATE_LIST);
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }

    public static String extractTagValue(String xmlPayload, String tagName) {
        try {
            xmlPayload = xmlPayload.trim();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(xmlPayload.getBytes()));
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(tagName);
            if (nodeList.getLength() > 0) {
                Element element = (Element) nodeList.item(0);
                return element.getTextContent();
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error("Error extracting tag value", e);
        }
        return null;
    }
}