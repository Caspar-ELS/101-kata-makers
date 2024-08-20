## Service status checker

This is a simple service status checker that shows the service instance is up or down.

## Requirements:

- [x] Show all BOM running microservices in DEV
- [x] Show all microservices, categorized by components (billing, order_management, fulfillment,
  revenue_recognition, core_bom, test_utilities)
- [ ] Show which component need to start for corresponding regression test cases, eg: we want to run Invoices regression test, `billing` component needs to start before running the test (you can assume `core_bom` is always on)
- [ ] ***Optional goal***: if the all the microservices in that component already started, then we don't have to ask the user to start that component
    - Example: We want to run GeneralLedgerRevenueRecognitionV3, require starting both `order_management` and `revenue_recognition` components, while orrx, orcx and oisu

## Example

```
billing
Name: trds, State: running
Name: trsb, State: not running
.....

order_management
Name: orrx, State: not running
Name: orcx, State: running
.....

To run regression test you have to start component:
Orders: order_management
Invoices: billing
CreditNotes: billing
TransactionStatuses: billing
AccountsReceivablesRevenueRecognition: order_management, fulfillment, test_utilities
GeneralLedgerRevenueRecognitionV3: order_management, revenue_recognition

```

## Useful information

Most of the information of a microservice comes from EC2 instance `tags` (you can refer to
Ec2Service.java)

| Tag                           | Description                                                   |
|-------------------------------|---------------------------------------------------------------|
| `Role`                        | Microservice abbv. (ORCX / RERCv3)                            |
| `Environment`                 | environment (dev / sit)                                       |
| `MicroService` / `SubProduct` | Full microservice name (revenue-recognition-status-router-v3) |

Components

| Tag                 | Description                                                                    |
|---------------------|--------------------------------------------------------------------------------|
| billing             | "trds", "trsb", "trdr", "inas", "cats", "atss", "trsr", "insr", "insp", "cnsp" |
| order_management    | "orcx", "orrx", "oisu"                                                         |
| fulfillment         | "fucx", "aofc", "arfx", "asft", "asfc", "pofc", "fusp"                         |
| revenue_recognition | "rrspv3", "rrsrv3", "rersv3", "reacv3", "rertv3", "arfs"                       |
| core_bom            | "bocs", "nesx"                                                                 |
| test_utilities      | "eier", "tekp"                                                                 |
