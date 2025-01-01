package org.example.bookmyshow3.controllers;

import org.example.bookmyshow3.dtos.BookMovieRequestDTO;
import org.example.bookmyshow3.dtos.BookMovieResponseDTO;
import org.example.bookmyshow3.dtos.ResponseStatus;
import org.example.bookmyshow3.models.Booking;
import org.example.bookmyshow3.models.BookingStatus;
import org.example.bookmyshow3.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDTO bookMovie(BookMovieRequestDTO bookMovieRequestDTO) {
        BookMovieResponseDTO bookMovieResponseDTO = new BookMovieResponseDTO();

        try{
            Booking booking = bookingService.bookMovie(bookMovieRequestDTO.getUserId(),
                    bookMovieRequestDTO.getShowSeatIds(),
                    bookMovieRequestDTO.getShowId());
            bookMovieResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            bookMovieResponseDTO.setBookingId(booking.getId());
            bookMovieResponseDTO.setAmount(booking.getTotalAmount());
        } catch (Exception ex){
            bookMovieResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return bookMovieResponseDTO;
    }
}
