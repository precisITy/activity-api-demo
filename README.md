# Activity REST API

## Intro

The REST API is specified using Open API and the service is based on Spring Boot has partially been generated using Swagger. The core class of the REST API is called "ActivityApiController".

The backend operates with a H2 database for storing the data. Note that the data is NOT removed from the database when the service is stopped or restarted. The database is a file saved in a folder called "database" (either created within the project, if executed within the IDE, or next to the JAR).

Dependencies are managed using Maven.

## Usage

The project was created using Eclipse 2019-09 and Java 8. Either import the project in Eclipse and execute the class "Application" or use the provided JAR.

Run the JAR by executing:

```
java -jar activity-api-1.0.0.jar
```

The service is then available at: http://localhost:8000

## Demo

The following steps can be executed using the Swagger-UI (http://localhost:8000/swagger-ui.html) or using your own tool of choice for sending the requests.

Add two activities:

```
POST: http://localhost:8000/activity
REQUEST BODY:
{
  "author": "Max",
  "dateCreation": "2019-12-05T16:44:46.992Z",
  "header": "myHeader",
  "name": "Activity A",
  "price": "45.5",
  "procurementChannel": "Channel 1",
  "quantity": 3,
  "shortDescription": "This is activity A",
  "status": "B"
}
RESPONSE: 201
RESPONSE BODY:
{
  "id": "1",
  "author": "Max",
  "dateCreation": "2019-12-05T16:44:46.992Z",
  "header": "myHeader",
  "name": "Activity A",
  "price": "45.5",
  "procurementChannel": "Channel 1",
  "quantity": 3,
  "shortDescription": "This is activity A",
  "status": "B"
}
```

```
POST: http://localhost:8000/activity
REQUEST BODY:
{
  "author": "Sven",
  "dateCreation": "2019-10-06T13:54:46.992Z",
  "header": "myHeader",
  "name": "Activity B",
  "price": "4",
  "procurementChannel": "Channel 2",
  "quantity": 1,
  "shortDescription": "This is activity B",
  "status": "C"
}
RESPONSE: 201
RESPONSE BODY:
{
  "id": "2",
  "header": "myHeader",
  "name": "Activity B",
  "shortDescription": "This is activity B",
  "procurementChannel": "Channel 2",
  "dateCreation": "2019-10-06T13:54:46.992Z",
  "author": "Sven",
  "quantity": 1,
  "price": "4",
  "status": "C"
}
```

Request a single activity:

```
GET: http://localhost:8000/activity/1
RESPONSE: 200
RESPONSE BODY:
{
  "id": "1",
  "header": "myHeader",
  "name": "Activity A",
  "shortDescription": "This is activity A",
  "procurementChannel": "Channel 1",
  "dateCreation": "2019-12-05T16:44:46.992Z",
  "author": "Max",
  "quantity": 3,
  "price": "45.5",
  "status": "B"
}
```

```
GET: http://localhost:8000/activity/5
RESPONSE: 404
```

Update an activity:

```
PUT: http://localhost:8000/activity/1
REQUEST BODY:
{
  "author": "Maxi",
  "dateCreation": "2019-12-05T16:44:46.992Z",
  "header": "myHeader",
  "name": "Activity A",
  "price": "41.2",
  "procurementChannel": "Channel 1",
  "quantity": 7,
  "shortDescription": "This is activity A",
  "status": "A"
}
RESPONSE: 200
RESPONSE BODY:
{
  "id": "1",
  "header": "myHeader",
  "name": "Activity A",
  "shortDescription": "This is activity A",
  "procurementChannel": "Channel 1",
  "dateCreation": "2019-12-05T16:44:46.992Z",
  "author": "Maxi",
  "quantity": 7,
  "price": "41.2",
  "status": "A"
}
```

Request all activities:

```
GET: http://localhost:8000/activity
RESPONSE: 200
RESPONSE BODY:
[
  {
    "id": "1",
    "header": "myHeader",
    "name": "Activity A",
    "shortDescription": "This is activity A",
    "procurementChannel": "Channel 1",
    "dateCreation": "2019-12-05T16:44:46.992Z",
    "author": "Maxi",
    "quantity": 7,
    "price": "41.2",
    "status": "A"
  },
  {
    "id": "2",
    "header": "myHeader",
    "name": "Activity B",
    "shortDescription": "This is activity B",
    "procurementChannel": "Channel 2",
    "dateCreation": "2019-10-06T13:54:46.992Z",
    "author": "Sven",
    "quantity": 1,
    "price": "4",
    "status": "C"
  }
]
```

Request all activities from author "Sven":

```
GET: http://localhost:8000/activity?author=Sven
RESPONSE: 200
RESPONSE BODY:
[
  {
    "id": "2",
    "header": "myHeader",
    "name": "Activity B",
    "shortDescription": "This is activity B",
    "procurementChannel": "Channel 2",
    "dateCreation": "2019-10-06T13:54:46.992Z",
    "author": "Sven",
    "quantity": 1,
    "price": "4",
    "status": "C"
  }
]
```

Request all activities that contain the keyword "ax" (as "Maxi" does):

```
GET: http://localhost:8000/activity?keyword=ax
RESPONSE: 200
RESPONSE BODY:
[
  {
    "id": "1",
    "header": "myHeader",
    "name": "Activity A",
    "shortDescription": "This is activity A",
    "procurementChannel": "Channel 1",
    "dateCreation": "2019-12-05T16:44:46.992Z",
    "author": "Maxi",
    "quantity": 7,
    "price": "41.2",
    "status": "A"
  }
]
```

Request all activities that have a creation date newer than (or equal to) a specific date:

```
GET: http://localhost:8000/activity?since=2019-12-01
RESPONSE: 200
RESPONSE BODY:
[
  {
    "id": "1",
    "header": "myHeader",
    "name": "Activity A",
    "shortDescription": "This is activity A",
    "procurementChannel": "Channel 1",
    "dateCreation": "2019-12-05T16:44:46.992Z",
    "author": "Maxi",
    "quantity": 7,
    "price": "41.2",
    "status": "A"
  }
]
```

Request all activities that have a creation date older than (or equal to) a specific date:

```
GET: http://localhost:8000/activity?since=2019-12-01
RESPONSE: 200
RESPONSE BODY:
[
  {
    "id": "2",
    "header": "myHeader",
    "name": "Activity B",
    "shortDescription": "This is activity B",
    "procurementChannel": "Channel 2",
    "dateCreation": "2019-10-06T13:54:46.992Z",
    "author": "Sven",
    "quantity": 1,
    "price": "4",
    "status": "C"
  }
]
```

Delete an activity:

```
DELETE: http://localhost:8000/activity/2
RESPONSE: 204

DELETE: http://localhost:8000/activity/7
RESPONSE: 404
```

