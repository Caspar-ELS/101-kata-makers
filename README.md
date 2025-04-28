# Rest API exercise

## Instructions

Implement a RESTful API for tracking IOUs.

Four roommates have a habit of borrowing money from each other frequently, and have trouble remembering who owes whom, and how much.

Your task is to implement a simple RESTful API that receives IOUs as POST requests, and can deliver specified summary information via GET requests.

## API Specification

### User object

```json 
{
  "name": "Adam",
  "owes": {
    "Bob": 12.0,
    "Chuck": 4.0,
    "Dan": 9.5
  },
  "owed_by": {
    "Bob": 6.5,
    "Dan": 2.75
  },
  "balance": "<(total owed by other users) - (total owed to other users)>"
}
```

### Methods

#### GET /users

Returns a list of all users in the system (no payload) or for specified users with payload provided, sorted by name.

Payload format:

```json
{
  "users": [
    "Adam",
    "Bob"
  ]
}
```

#### POST /add

Adds a user to the system. Name must be unique. 

#### POST /iou

Add an IOU to the system. 

Payload format:

```json
{
  "lender": "name of lender",
  "borrower": "name of borrower",
  "amount": 5.25
}
```

Link to original exercise: https://exercism.org/tracks/java/exercises/rest-api
