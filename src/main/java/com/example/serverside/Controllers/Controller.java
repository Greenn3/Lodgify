package com.example.serverside.Controllers;


import com.example.serverside.Entities.AccType;
import com.example.serverside.Entities.Booking;
import com.example.serverside.Entities.PricePerType;
import com.example.serverside.Entities.PricePeriod;
import com.example.serverside.Services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
public class Controller {




    Booking bookingForPriceCalculation;
    private static final String API_KEY = "rjfghreohgaojfjeorjpw45i945jijJDI3J";;


    // Utility method to check the API key
    private boolean isValidApiKey(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.equals("Bearer " + API_KEY);
    }
    private final Service service;
    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

@GetMapping("/check")
public ResponseEntity<String> check() {
        return ResponseEntity.ok("OK");
}

@GetMapping("/checkIfFree")
public ResponseEntity<Boolean> checkIfFree(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                           @RequestParam("arrivalDate") String arrivalDate,
                                           @RequestParam("departureDate") String departureDate,
                                           @RequestParam("type") String typeId) {
    if (!isValidApiKey(authorizationHeader)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

AccType type = service.getAccTypeById(Integer.parseInt(typeId));
    LocalDate arrDate = LocalDate.parse(arrivalDate);
    LocalDate depDate = LocalDate.parse(departureDate);
    System.out.println("response " + ResponseEntity.ok(service.checkIfFree(arrDate, depDate, type)));
        return ResponseEntity.ok(service.checkIfFree(arrDate, depDate, type));
}


    @GetMapping("/allBookings")
    public ResponseEntity<List<Booking>> getBookings(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        List<Booking> bookings = service.getBookings();
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/addBooking")
    public ResponseEntity<Void> saveBooking(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestBody Booking booking) {

        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        service.addNewBooking(booking);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getBookingByArrivalDate")
    public ResponseEntity<List<Booking>> findBookingsByArrivalDate(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestParam("date") String date) {

        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(service.getBookingByArrivalDate(localDate));
    }

    @GetMapping("/getBookingByDepartureDate")
    public ResponseEntity<List<Booking>> findBookingsByDepartureDate(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestParam("date") String date) {

        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(service.getBookingByDepartureDate(localDate));
    }

    @GetMapping("/getBookingByName")
    public ResponseEntity<List<Booking>> getBookingByName(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestParam("name") String name) {
        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.getBookingByName(name));
    }

    @GetMapping("/getBookingByPaymentStatus")
    public ResponseEntity<List<Booking>> getBookingByPaymentStatus(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestParam("paid") Boolean isPaid) {
        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.getBookingByPaymentStatus(isPaid));
    }


    @GetMapping("/types")
    public ResponseEntity<List<AccType>> getAccTypes(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(service.getAccTypes());
    }

    @GetMapping("/periods")
    public ResponseEntity<List<PricePeriod>> getPricePeriods(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(service.getPricePeriod());
    }

    @GetMapping("/getPrices")
    public ResponseEntity<List<PricePerType>> getPrices(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(service.getPrices());
    }

    @PostMapping("/updatePriceList")
    public ResponseEntity<Void> updatePriceList(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestBody List<PricePerType> pricePerType) {

        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        service.updateObjects(pricePerType);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sendPriceData")
    public ResponseEntity<Void> sendPriceList(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestBody Booking booking) {

        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        bookingForPriceCalculation = booking;
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getPrice")
    public ResponseEntity<Double> getPrice(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader)

    {

        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(service.calculatePrice(bookingForPriceCalculation));
    }

    @GetMapping("/getBookingById")
    public ResponseEntity<Booking> findBookingById(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestParam("id") Integer id) {

        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        System.out.println("Tutu");
        return ResponseEntity.ok(service.findBookingById(id));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBooking(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestParam("id") Integer id) {
        if (!isValidApiKey(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        service.deleteBookingById(id);
        System.out.println("Usuwamy");
        return ResponseEntity.ok().build();
    }
    }








