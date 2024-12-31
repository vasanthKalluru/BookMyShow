package org.example.bookmyshow3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.bookmyshow3.models.ShowSeat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);



}
