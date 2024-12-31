package org.example.bookmyshow3.services;

import org.example.bookmyshow3.models.Booking;
import org.example.bookmyshow3.repos.BookingRepository;
import org.example.bookmyshow3.repos.ShowRepository;
import org.example.bookmyshow3.repos.ShowSeatRepository;
import org.example.bookmyshow3.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, List<Long> seatIds, Long showId  ) {
        /*
        * --- Start Trx here for today
        * 1. Get User from the database
        * 2. Fetch show from db
        * ----Transaction ------
        * 3. Get showSeat from db
        * 4. Check if they are available
        * 5. If not, throw error
        * 6. If yes, update the status to locked
        * 7. save the DB
        * ----End transaction-----
        * 8. Create Booking Object and set the attributes
        * 9. Return to the controller.
        * --- End Trx here for today
        * */
        return null;
    }
}
