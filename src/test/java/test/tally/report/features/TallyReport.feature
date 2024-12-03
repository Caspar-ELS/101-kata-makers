Feature: Tally Report for Integrity tests

  @InvoiceSubmittedToR12
  Scenario: All invoices submitted to R12 in given time span
    When Retrieve "event=" for "\"Created InvoiceDrafted event\" AND \"CRM_MQ2COM\"" from "transaction-details-receiver" between "2024-11-06T13:00:00.000Z" and "2024-12-01T16:15:00.000Z"
    Then Verify test data for "\"<REPLACE_WITH_INVOICE_ID>\" AND \"InvoiceSubmitted\" AND \"Message produced to\"" from "transaction-details-sender" between "2024-11-06T13:00:00.000Z" and "2024-12-01T16:15:00.000Z"
    And Create Test Tally Report