package org.example.bookmyshow3.dtos;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookMovieRequestDTO {
    private List<Long> showSeatIds;
    private Long userId;
    private Long showId;

}
