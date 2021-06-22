# FetchRewardBackendExercise
## Description:
### A web service that accepts HTTP requests for room booking and returns responses consist of list of booked rooms and conflict rooms based which are based on the priority.
### If same room have overlap request then request are denied on basis of priority ( default is 0).
### 0 as lowest and increasing order highest priority.
### Required parameters : 
 "roomNumber" : Int (required),
 "checkIn" : Date (required),
 "checkOut" : Date (required),
"priority" : Int (Optional default is 0 - lowest)


## How to run using command line
#### Download the provided jar file
#### Go to the same folder in which BookingRequestManagement.jar has been downloaded and run the jar file using the following command
java -jar BookingRequestManagement.jar --server.port=8090

## Using curl command to make GET and POST request:

### Method type : POST
### Method name : recieveBookRequest
### Method Response : 
Map of room number and array of room booking status with checkin and checkout time, and priority
List of conflicts room (if any)
Http Status


curl --location --request POST 'http://localhost:8090/book/request' \
--header 'Content-Type: application/json' \
--data-raw '{
    "roomNumber" : 2,
    "checkIn" : "2021-13-02T15:10:00Z",
    "checkOut" : "2021-13-02T15:30:00Z",
    "priority" : 1
}'



curl --location --request POST 'http://localhost:8090/book/request' \
--header 'Content-Type: application/json' \
--data-raw '{
    "roomNumber" : 2,
    "checkIn" : "2021-13-02T16:10:00Z",
    "checkOut" : "2021-13-02T16:30:00Z",
    "priority" : 2
}'

curl --location --request POST 'http://localhost:8090/book/request' \
--header 'Content-Type: application/json' \
--data-raw '{
    "roomNumber" : 1,
    "checkIn" : "2021-13-02T15:10:00Z",
    "checkOut" : "2021-13-02T15:30:00Z",
    "priority" : 1
}'


### Method type : POST (to create conflict for room number : 1 with priority 3)
### Method name : recieveBookRequest
### Method Response : 
Map of room number and array of room booking status with checkin and checkout time, and priority
List of conflicts room (if any)
Http Status

curl --location --request POST 'http://localhost:8090/book/request' \
--header 'Content-Type: application/json' \
--data-raw '{
    "roomNumber" : 1,
    "checkIn" : "2021-13-02T16:10:00Z",
    "checkOut" : "2021-13-02T16:30:00Z",
    "priority" : 3
}'


### Method type : GET 
### Method name : getAllBooking
### Method Response : 
Map of room number and array of room booking status with checkin and checkout time, and priority
Empty List of conflicts room
Http Status

curl --location --request GET 'http://localhost:8090/allbooking'


# ---------------------- OR ---------------------

## How to run using Intellij:
#### Download complete project from github.
#### Open in IDE(IntellIJ) build and run project.

## Use postman to make GET and POST request.

### Send Booking request using postman:
#### Method type : POST
#### http://localhost:8090/book/request (localHost/book/request)
#### Parameter : In body : {
    "roomNumber" : 2, 
    "checkIn" : "2021-13-02T15:10:00Z", 
    "checkOut" : "2021-13-02T15:30:00Z", 
    "priority" : 1
}
#### Return type : HttpStatus : Return : HttpStatus: Ok : success,  BADREQUEST : if datatype/parameter are invalid or missing


### Check booking status of all room using Browser
#### Method type : GET
#### http://localhost:8090/allbooking
#### Return type : 
List of allBookings based on room number as shown below:

{"conflictRequestList":null,
"status":"OK",
"allBookings":
{"1":
[{"roomNumber":1,"checkIn":"2022-01-02T16:10:00Z","checkOut":"2022-01-02T16:30:00Z","priority":3},
{"roomNumber":1,"checkIn":"2022-01-02T15:10:00Z","checkOut":"2022-01-02T15:30:00Z","priority":1}],
"2":
[{"roomNumber":2,"checkIn":"2022-01-02T16:10:00Z","checkOut":"2022-01-02T16:30:00Z","priority":2},
{"roomNumber":2,"checkIn":"2022-01-02T15:10:00Z","checkOut":"2022-01-02T15:30:00Z","priority":1}]}}



