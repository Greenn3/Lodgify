package com.example.serverside;


import com.example.serverside.Entities.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")

public class BookingEntityTest {

    @Test
    public void testBookingSettersAndGetters() {
        Booking booking = new Booking();
        booking.setId(1);
        booking.setName("Test User");
        booking.setArrivalDate(LocalDate.of(2025, 1, 1));
        booking.setDepartureDate(LocalDate.of(2025, 1, 10));
        booking.setEmail("test@example.com");
        booking.setPhone("123456789");
        booking.setInfo("Extra info");
        booking.setHasArrived(true);
        booking.setHasLeft(false);
        booking.setPaid(true);
        booking.setDiscount(15.0);

        assertEquals(1, booking.getId());
        assertEquals("Test User", booking.getName());
        assertEquals(LocalDate.of(2025, 1, 1), booking.getArrivalDate());
        assertEquals(LocalDate.of(2025, 1, 10), booking.getDepartureDate());
        assertEquals("test@example.com", booking.getEmail());
        assertEquals("123456789", booking.getPhone());
        assertEquals("Extra info", booking.getInfo());
        assertTrue(booking.isHasArrived());
        assertFalse(booking.isHasLeft());
        assertTrue(booking.isPaid);
        assertEquals(15.0, booking.getDiscount());
    }
}
