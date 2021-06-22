package Pojo;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class BookingRequestResponse {
    List<BookingRequest> conflictRequestList;
    HttpStatus status;
    Map<Integer, List<BookingRequest>> allBookings;

    public Map<Integer, List<BookingRequest>> getAllBookings() {
        return allBookings;
    }

    public void setAllBookings(Map<Integer, List<BookingRequest>> allBookings) {
        this.allBookings = allBookings;
    }

    public List<BookingRequest> getConflictRequestList() {
        return conflictRequestList;
    }
    public void setConflictRequestList(List<BookingRequest> conflictRequestList) {
        this.conflictRequestList = conflictRequestList;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
