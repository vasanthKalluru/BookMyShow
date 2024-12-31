package org.example.bookmyshow3.controllers;

import org.example.bookmyshow3.dtos.BookMovieRequestDTO;
import org.example.bookmyshow3.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void bookMovie(BookMovieRequestDTO bookMovieRequestDTO) {


    }
}
