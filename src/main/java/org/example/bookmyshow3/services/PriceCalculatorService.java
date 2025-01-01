package org.example.bookmyshow3.services;

import org.example.bookmyshow3.models.Show;
import org.example.bookmyshow3.models.ShowSeat;
import org.example.bookmyshow3.models.ShowSeatType;
import org.example.bookmyshow3.repos.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(Show show, List<ShowSeat> showSeats) {
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);
        int amount = 0;
        for(ShowSeat showSeat : showSeats) { //ShowSeats selected by the user
            for(ShowSeatType showSeatType : showSeatTypes) { //showseatTypes present in the entire show
                if(showSeatType.getSeatType().equals(showSeat.getSeat().getSeatType())) {
                    amount += showSeatType.getPrice();
                }
            }
        }
        return amount;
    }

}
