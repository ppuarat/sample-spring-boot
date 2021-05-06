package com.example.base.controller;

import com.example.base.model.Booking;
import com.example.base.model.BookingRequest;
import com.example.base.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("bookings")
public class BookingController {

    private List<Booking> bookingList = new ArrayList<>();
    private AtomicLong counter = new AtomicLong();

    public BookingController() {
        this.bookingList.add(new Booking(counter.incrementAndGet(),
                new User(1L, "John Doe"),
                "Monday",
                15));
    }

    @GetMapping
    public List<Booking> getBooking() {
        return bookingList;
    }

    @GetMapping(path = "{userId}")
    public List<Booking> getBookingListFromUserId(@PathVariable Long userId){

        return bookingList.stream().filter(booking -> {
            if(booking.getUser().getId() == userId){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public Booking book(@RequestBody BookingRequest bookingRequest) throws Exception {
        boolean isBooked = isUserBooked(bookingRequest);
        boolean isAvailableToBook = checkTimeAvailability(bookingRequest);

        Booking newBooking;
        if (!isBooked && isAvailableToBook) {
            newBooking = new Booking(counter.incrementAndGet(),
                    new User(bookingRequest.getUserId(), ""),
                    bookingRequest.getDay(),
                    bookingRequest.getTime());
            bookingList.add(newBooking);
        } else {
            System.out.println("User has booked: " + isBooked);
            System.out.println("This time is available: " + isAvailableToBook);
            throw new Exception("Wont we able to book");
        }
        return newBooking;
    }

    private boolean isUserBooked(BookingRequest bookingRequest) {
        return bookingList.stream().filter(booking -> {
            if (booking.getUser().getId() == bookingRequest.getUserId()
                    && booking.getBookedDay().equalsIgnoreCase(bookingRequest.getDay())) {
                return true;
            }
            return false;
        }).findFirst().isPresent();
    }

    private boolean checkTimeAvailability(BookingRequest bookingRequest) {
        List<Booking> bookedTime = bookingList.stream().filter(booking -> {
            if (booking.getBookedDay().equalsIgnoreCase(bookingRequest.getDay())
                    && booking.getTime() == bookingRequest.getTime()) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        return bookedTime.size() < 8;
    }

}
