package enums;

import lombok.Getter;

@Getter
public enum Test {
  ORDERS("Orders"),
  INVOICES("Invoices"),
  CREDIT_NOTES("CreditNotes"),
  TRANSACTION_STATUSES("TransactionStatuses"),
  ACCOUNTS_RECEIVABLES_REVENUE_RECOGNITION("AccountsReceivablesRevenueRecognition"),
  GENERAL_LEDGER_REVENUE_RECOGNITION_V3("GeneralLedgerRevenueRecognitionV3");

  private String name;

  Test(String name) {
    this.name = name;
  }
}
