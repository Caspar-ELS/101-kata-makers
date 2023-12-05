# Dec 05 Java 101

## Ecommerce system (Cont')

### 1. Adding product name into the list

- modify your function implemented previously, making it able to save product name into the list
- check if duplicate product name exist, if yes, throw error
  - example:
    - 1st: ("chocolate cake", 19) -> OK
    - 2nd: ("cheesecake", 38) -> OK
    - 3rd: ("chocolate cake", 20) -> throw error, thought the name is not correct

### 2. Apply discount to the sum
- create a member variable, storing discount percentage off (if we would like offer 10% off, input should be 10)
- When calculate total price:
  - if discount exist in shopping cart -> return price with discount
  - if discount not exist in shopping cart -> return price without any discount


