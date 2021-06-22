package Service;

import Pojo.BookingRequest;
import Pojo.BookingRequestResponse;
import org.springframework.http.HttpStatus;

import java.util.*;

public class BookRequestService {
    Map<Integer, List<BookingRequest>> requestMap;

    public BookRequestService(){
        requestMap = new HashMap<>();
    }

    /**
     * This function checks if incoming request to book room has valid parameters or not.
     * It checks if map contains given room number in request as key. If so check conflicts, also update them.
     * Else add entry into map.
     * @param request
     * @return BookingRequestResponse that contains
     *          list of conflicts request that are deny(if any).
     *          Current status of all booked rooms
     *          HttpStatus - OK - successful
     *                     - BADREQUEST - invalid or missing parameters.
     */
    public BookingRequestResponse addRequest(BookingRequest request){
        List<BookingRequest> conflictRequestList = new ArrayList<>();
        List<BookingRequest> bookingList = new ArrayList<>();

        BookingRequestResponse bookingRequestResponse = new BookingRequestResponse();

        if(request == null ||
                request.getCheckIn() == null ||
                request.getCheckOut() == null ||
                request.getCheckIn().compareTo(request.getCheckOut()) > 0 ||
                request.getRoomNumber() < 0){
            bookingRequestResponse.setAllBookings(requestMap);
            bookingRequestResponse.setConflictRequestList(null);
            bookingRequestResponse.setStatus(HttpStatus.BAD_REQUEST);
            return bookingRequestResponse;
        }

        if(requestMap.isEmpty() || !requestMap.containsKey(request.getRoomNumber())){
            bookingList.add(request);
            requestMap.put(request.getRoomNumber(), bookingList);

            bookingRequestResponse.setAllBookings(requestMap);
            bookingRequestResponse.setConflictRequestList(null);
            bookingRequestResponse.setStatus(HttpStatus.OK);

            return bookingRequestResponse;
        }

        if(requestMap.containsKey(request.getRoomNumber())){
            List<BookingRequest> allBookingforGivenRoom = requestMap.get(request.getRoomNumber());

            //sorting list based on priority from lowest to highest
            // so that one with lowest priority get deny
            Collections.sort(allBookingforGivenRoom, new Comparator<BookingRequest>(){
                @Override
                public int compare(BookingRequest b1 , BookingRequest b2){
                    return Integer.compare(b1.getPriority(), b2.getPriority());
                }
            });

            Date s1 = request.getCheckIn();
            Date e1 = request.getCheckOut();
            boolean addGivenRequestToConflict = false;

            for(BookingRequest req : allBookingforGivenRoom){
                Date s2 = req.getCheckIn();
                Date e2 = req.getCheckOut();

                //compare and check conflicts between given request and currently available
                if(s1.compareTo(e2) < 0 && s2.compareTo(e1) < 0){
                    if(request.getPriority() > req.getPriority()){
                        req.setCheckIn(null);
                        req.setCheckOut(null);
                        conflictRequestList.add(req);
                    }else{
                        addGivenRequestToConflict = true;
                    }
                }
            }

            List<BookingRequest> updatedBookingforGivenRoom = new ArrayList<>();
            if(addGivenRequestToConflict){
                conflictRequestList.add(request);
            }else{
                updatedBookingforGivenRoom.add(request);
            }

            for(BookingRequest req : allBookingforGivenRoom){
                if(req.getCheckOut() != null && req.getCheckIn() != null){
                    updatedBookingforGivenRoom.add(req);
                }
            }

           requestMap.put(request.getRoomNumber(), updatedBookingforGivenRoom);
        }

        bookingRequestResponse.setAllBookings(requestMap);
        bookingRequestResponse.setConflictRequestList(conflictRequestList);
        bookingRequestResponse.setStatus(HttpStatus.OK);
        return bookingRequestResponse;
    }

    /**
     * Get all booked rooms status.
     * @return BookingRequestResponse with list of all booked rooms and HTTPStatus - OK
     */
    public BookingRequestResponse getAllBookings(){
        BookingRequestResponse bookingRequestResponse = new BookingRequestResponse();
        bookingRequestResponse.setStatus(HttpStatus.OK);
        bookingRequestResponse.setConflictRequestList(null);
        bookingRequestResponse.setAllBookings(requestMap);
        return bookingRequestResponse;
    }
}
