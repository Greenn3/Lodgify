package com.example.serverside.Repositories;


import com.example.serverside.Entities.AccType;
import com.example.serverside.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
List<Booking> findBookingByArrivalDate(LocalDate arrivalDate);

List<Booking> findBookingByDepartureDate(LocalDate departureDate);

Booking findBookingById(Integer id);

    List<Booking> findBookingByName(String name);

    List<Booking> findBookingByIsPaid(Boolean isPaid);

Booking deleteBookingById(Integer id);


    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END " +
            "FROM Booking b " +
            "WHERE b.accType = :accType " +
            "AND ( (b.arrivalDate < :date AND b.departureDate > :date) " +
            "OR b.arrivalDate = :arrDate )")
    boolean existsBookingWithinDateRangeAndAccType(@Param("date") LocalDate date,
                                                  @Param("arrDate") LocalDate arrDate,
                                                   @Param("accType") AccType accType);
}
