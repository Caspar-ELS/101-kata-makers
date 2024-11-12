Spot a cat

Main requirement:
- build an API that checks if a given object represents a cat

Fields:
- number_of_paws (int) - mandatory
- has_tail (boolean)
- is_tail_long (boolean)
- is_carnivore (boolean)
- has_whiskers (boolean)
- has_retractable_claws (boolean)
- purrs (boolean)
- meows (boolean)
- is_domesticated (boolean)

| fields                | cat        | dog        | fox   | genet | fossa | other |
|-----------------------|------------|------------|-------|-------|-------|-------|
| number_of_paws        | 4          | 4          | 4     | 4     | 4     | any   |
| ---                   | ---        | ---        | ---   | ---   | ---   | ---   |
| has_tail              | true       | true       | true  | true  | true  | any   |
| ---                   | ---        | ---        | ---   | ---   | ---   | ---   |
| is_tail_long          | true/false | true/false | true  | true  | true  | any   |
| ---                   | ---        | ---        | ---   | ---   | ---   | ---   |
| is_carnivore          | true       | true       | true  | true  | true  | any   |
| ---                   | ---        | ---        | ---   | ---   | ---   | ---   |
| has_whiskers          | true       | true       | true  | true  | true  | any   |
| ---                   | ---        | ---        | ---   | ---   | ---   | ---   |
| has_retractable_claws | true       | false      | true  | true  | false | any   |
| ---                   | ---        | ---        | ---   | ---   | ---   | ---   |
| purrs                 | yes        | no         | yes   | yes   | yes   | any   |
| ---                   | ---        | ---        | ---   | ---   | ---   | ---   |
| meows                 | true       | false      | false | false | false | false |
| ---                   | ---        | ---        | ---   | ---   | ---   | ---   |
| is_domesticated       | true/false | true/false | false | false | false | any   |

First phase:
- build an API that accepts json payload describing an animal
- return "Meow" if it is a cat

Second phase:
- if it is not a cat, return "Not a cat"
- if not sure whether it is a cat, give other possible answers (dog, fox, genet, fossa)
- exception handling (invalid json, missing mandatory fields, etc)