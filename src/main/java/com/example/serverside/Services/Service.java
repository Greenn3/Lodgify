package com.example.serverside.Services;


import com.example.serverside.Entities.AccType;
import com.example.serverside.Entities.Booking;
import com.example.serverside.Entities.PricePerType;
import com.example.serverside.Entities.PricePeriod;
import com.example.serverside.Repositories.AccTypeRepository;
import com.example.serverside.Repositories.BookingRepository;
import com.example.serverside.Repositories.PricePerTypeRepository;
import com.example.serverside.Repositories.PricePeriodRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class Service {


    private final BookingRepository bookingRepository;
    private final AccTypeRepository accTypeRepository;
    private final PricePeriodRepository pricePeriodRepository;

  private final PricePerTypeRepository pricePerTypeRepository;


 @Autowired
 public Service(BookingRepository bookingRepository, AccTypeRepository accTypeRepository, PricePeriodRepository pricePeriodRepository, PricePerTypeRepository pricePerTypeRepository) {
        this.bookingRepository = bookingRepository;
     this.accTypeRepository = accTypeRepository;
     this.pricePeriodRepository = pricePeriodRepository;
     this.pricePerTypeRepository = pricePerTypeRepository;
 }

    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }

    public boolean checkIfFree(LocalDate arrivalDate, LocalDate departureDate, AccType type) {
        List<LocalDate> list = new ArrayList<>();

        // Add all days in the range, including arrivalDate
        LocalDate currentDate = arrivalDate;
        while (!currentDate.isAfter(departureDate.minusDays(1))) { // Check until departureDate - 1
            list.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        // Check if any date in the range is already booked
        for (LocalDate d : list) {
            if (bookingRepository.existsBookingWithinDateRangeAndAccType(d, d, type)) {
                return false; // If any date is booked, return false immediately
            }
        }

        return true; // If no conflicts found, return true
    }
    public void addNewBooking(Booking booking) {
        bookingRepository.save(booking);
        System.out.println(booking.isPaid());
    }

    public List<AccType> getAccTypes() {return accTypeRepository.findAll();
    }
    public List<PricePeriod> getPricePeriod(){
     return pricePeriodRepository.findAll();
    }

    public List<PricePerType> getPrices(){
     return pricePerTypeRepository.findAll();
    }

public List<Booking> getBookingByArrivalDate(LocalDate arrivalDate)
{
     return bookingRepository.findBookingByArrivalDate(arrivalDate);
 }

    public List<Booking> getBookingByDepartureDate(LocalDate departureDate) {
        return bookingRepository.findBookingByDepartureDate(departureDate);
    }

    public void updateObjects(List<PricePerType> list) {
        for (PricePerType ppt : list) {
            Optional<PricePerType> existingObjectOptional = pricePerTypeRepository.findById(ppt.getId());
            if (existingObjectOptional.isPresent()) {
                PricePerType existingObject = existingObjectOptional.get();
                existingObject.setPrice(ppt.getPrice());
                pricePerTypeRepository.save(existingObject);
            }
        }
    }
    public Double calculatePrice(Booking booking){

        LocalDate start = booking.getArrivalDate();
        System.out.println("start = " + start);
        LocalDate end = booking.getDepartureDate();
        System.out.println("end = " + end);
        AccType accType = booking.getAccType();
        System.out.println("accTYpe in calculate Price method: " + accType);
        List<LocalDate> datesInBooking = start.datesUntil(end.plusDays(0))
                .collect(Collectors.toList());
        for(LocalDate d : datesInBooking) {
            System.out.println(d.toString());
        }
     Double price = 0.0;

        for(LocalDate d : datesInBooking){
            price += getPricePerNight(d, accType);

        }
        System.out.println("price = " + price);
        price = price* (1-(booking.getDiscount()/100));
        System.out.println("price = " + price);
        System.out.println("discount = " + booking.getDiscount());
     return price;
    }
    public Double getPricePerNight(LocalDate date, AccType accType){
     List<PricePeriod> pricePeriodList = pricePeriodRepository.findAll();

     double price= 0.0;
     for(PricePeriod pricePeriod : pricePeriodList){
         MonthDay dateMd = MonthDay.from(date);
         MonthDay startMd = MonthDay.from(pricePeriod.getStartTime());
         MonthDay endMd = MonthDay.from(pricePeriod.getEndTime());
             if(dateMd.isAfter(startMd) && dateMd.isBefore(endMd))
         {
             price = pricePerTypeRepository.findByPricePeriodAndAccType(pricePeriod, accType).getPrice();
         }
     }
     return price;
    }
    public Booking findBookingById(Integer id) {
     return bookingRepository.findBookingById(id);
    }

    public List<Booking> getBookingByName(String name) {
     return bookingRepository.findBookingByName(name);
    }

    public List<Booking> getBookingByPaymentStatus(Boolean isPaid) {
     return bookingRepository.findBookingByIsPaid(isPaid);
    }

    public AccType getAccTypeById(Integer id) {
     return accTypeRepository.findAccTypeById(id);
    }
    @Transactional
    public void deleteBookingById(Integer id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id); // Transactional method
        } else {
            throw new EntityNotFoundException("Entity with id " + id + " not found.");
        }

    }
}
