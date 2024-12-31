package org.example.bookmyshow3.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    //M:M mapping between booking and showseats consider cancellation of bookings
    @ManyToMany
    private List<ShowSeat> showSeats;
    @ManyToOne
    private User user;
    private int totalAmount;
    private Date bookedAt;
    @ManyToOne
    private Show show;
    @OneToMany
    private List<Payment> payments;
}
