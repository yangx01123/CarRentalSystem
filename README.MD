# Car Rental System
A system that lets a customer to reserve a car of a given type at a desired date and time for a given number of days.

For a new reservation, the system can detect if any peak time that certain car type may be overbooked or not.

### Prerequisites
For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

### Installing
```
cd <root directory where pom.xml file resides>
mvn clean install
```

### Configuration file
- application.properties

### Initial data populating
Make necessary modification on sql file if initial data is needed:
- data.sql

### Start service
```
cd target
java -jar carrentalsystem-<version>.jar
```

### Use cases

##### Find reservation by car type
```sh
curl -X GET "http://localhost:8080/find?type=compact" -H "Content-Type: application/json"
```
Response example
```json
[{"id":3,"cartype":"compact","startdt":"2018-09-11 12:19:33+0000","days":5,"enddt":"2018-09-16 12:19:33+0000"}]
```
##### Find reservation by id
```sh
curl -X GET "http://localhost:8080/find?id=1" -H "Content-Type: application/json"
```
Response example
```json
[{"id":1,"cartype":"sedan","startdt":"2018-09-17 18:47:52+0000","days":3,"enddt":"2018-09-20 18:47:52+0000"}]
```
##### Find all reservations
```sh
curl -X GET http://localhost:8080/find -H "Content-Type: application/json"
```
Response example
```json
[{"id":1,"cartype":"sedan","startdt":"2018-09-17 18:47:52+0000","days":3,"enddt":"2018-09-20 18:47:52+0000"},
{"id":2,"cartype":"fullsize","startdt":"2018-10-17 18:47:52+0000","days":4,"enddt":"2018-10-21 18:47:52+0000"},
{"id":3,"cartype":"compact","startdt":"2018-09-11 12:19:33+0000","days":5,"enddt":"2018-09-16 12:19:33+0000"},
{"id":6,"cartype":"luxury","startdt":"2018-09-20 15:17:39+0000","days":2,"enddt":"2018-09-22 15:17:39+0000"}]
```
##### Add a reservation
```sh
curl -X POST http://localhost:8080/add -H "Content-Type: application/json" \
-d "{\"cartype\":\"compact\",\"startdt\":\"2018-09-10 15:17:39+0000\",\"days\":3,\"enddt\":\"2018-09-21 15:17:39+0000\"}"
```
Response example (success)
```
'compact' reserved successfully with ID: 7.
```
Response example (failure) when overbook happens
```json
{
    "timestamp": "2018-09-23 23:28:14+0000",
    "status": 404,
    "error": "Not Found",
    "message": "'compact' is not available.",
    "path": "/add"
}
```
##### Change a reservation
```
curl -X PUT "http://localhost:8080/change?id=3" -H "Content-Type: application/json" \
-d "{ \"cartype\": \"sedan\", \"startdt\": \"2018-09-11 12:19:33+0000\", \"days\": 2, \"enddt\": \"2018-09-16 12:19:33+0000\"}"
```
Response example (success)
```
Reservation '3' was updated successfully!
```
Response example (failure) when overbook happens
```json
{
    "timestamp":"2018-09-24 02:18:42+0000",
    "status":404,
    "error":"Not Found",
    "message":"'sedan' is not available.",
    "path":"/change"
}
```

##### Cancel a reservation
```sh
curl -X DELETE "http://localhost:8080/delete?id=5" -H "Content-Type: application/json"
```
Response (successful) `1`. Response (not exist) `0`

## Testing tools
- [Postman](https://www.getpostman.com)
- [Advanced REST client](http://advancedrestclient.com/)
- curl command (above use cases examples)
