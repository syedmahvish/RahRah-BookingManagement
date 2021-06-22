package com.example.BookingRequestManagement;

import Pojo.BookingRequest;
import Pojo.BookingRequestResponse;
import Service.BookRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingRequestController {
    BookRequestService bookRequestService = new BookRequestService();

    /**
     * This function accepts room booking request with BookingRequest as parameter.
     * Add this request to in-memory storage for room.
     * @param request : BookingRequest (roomNumber, checkin datetime, checkout datetime, priority - default is 0)
     * @return BookingRequestResponse that contains
     * list of conflict Request which are deny(if any).
     * Current booking status of all rooms.
     * HttpStatus  - OK if successful
     *              - BAD REQUEST - if parameters are missing or checkin > checkout
     */
   @PostMapping("/book/request")
   public ResponseEntity<BookingRequestResponse>  recieveBookRequest(@RequestBody BookingRequest request){
        BookingRequestResponse bookingRequestResponse = bookRequestService.addRequest(request);
        return new ResponseEntity(bookingRequestResponse, bookingRequestResponse.getStatus());
   }

    /**
     * This functions gets list of all rooms with booking status.
     * @return BookingRequestResponse with Current booking status of all rooms
     */
   @GetMapping("/allbooking")
    public ResponseEntity<BookingRequestResponse> getAllBooking(){
       BookingRequestResponse bookingRequestResponse = bookRequestService.getAllBookings();
       return new ResponseEntity(bookingRequestResponse, bookingRequestResponse.getStatus());
   }

}
