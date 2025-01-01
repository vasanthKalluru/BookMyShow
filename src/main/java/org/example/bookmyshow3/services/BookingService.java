package org.example.bookmyshow3.services;

import org.example.bookmyshow3.models.*;
import org.example.bookmyshow3.repos.BookingRepository;
import org.example.bookmyshow3.repos.ShowRepository;
import org.example.bookmyshow3.repos.ShowSeatRepository;
import org.example.bookmyshow3.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;
    private PriceCalculatorService priceCalculatorService;

    public BookingService(BookingRepository bookingRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          UserRepository userRepository,
                          PriceCalculatorService priceCalculatorService) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, List<Long> showSeatIds, Long showId  ) {
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

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found in the database"); //TODO: create UserNotFoundException
        }
        User bookedBy= optionalUser.get();

        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()) {
            throw new RuntimeException("Show not found in the database");
        }
        Show bookedShow = optionalShow.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for(ShowSeat showSeat : showSeats) {
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED)
                            && Duration.between(showSeat.getBlockedAt().toInstant(),new Date().toInstant()).toMinutes() > 15 ))) {
                throw new RuntimeException("Show seat has not been blocked");
            }
        }

        List<ShowSeat> bookedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());
            bookedShowSeats.add(showSeatRepository.save(showSeat));
        }

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(bookedShowSeats);
        booking.setShow(bookedShow);
        booking.setUser(bookedBy);
        booking.setBookedAt(new Date());
        booking.setTotalAmount(priceCalculatorService.
                calculatePrice(bookedShow,showSeats));
        booking.setPayments(new ArrayList<>());
        Booking savedBooking = bookingRepository.save(booking);

        return savedBooking;
    }
}
