package Pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;


public class BookingRequest {
    int roomNumber;
    @JsonFormat(shape = JsonFormat.Shape.ANY, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date checkIn;

    @JsonFormat(shape = JsonFormat.Shape.ANY, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date checkOut;

    Integer priority = 0;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
