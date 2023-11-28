# Nov 28 Java 101

## Ecommerce system (Cont')
### 1. Continue with the summation function from last week, this time separator can be any string
Requirements: 
- at least two number (e.g.: `1` / `1,` -> not accepted; `2,4` -> accepted and return 6)
- separator can be any string but the string should have length = 1
  - if the separator is empty / null / having length > 1, throw an exception

you may assume there only number and delimiter will be appeared in the function parameter
For example: `calculate("1]2]3", "]")`

Hint: `Pattern.quote()` may help, it is a function for converting reserved character in regex in normal string

### 2. Create a class called `ShoppingCart`
- has 1 member variable containing list of product price (you may assume the price may not only be integer, i.e.: the input maybe include floating point!(12, 12.3, 12.68 can be the price))
- implement function to add product price to the list
- implement function to calculate function to calculate current total price
  - e.g.: added 3 items with price 10, 20, 30, when calculate total price, it should return 60


