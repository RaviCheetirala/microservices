package com.wow.erc.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wow.erc.booking.dao.BookingDAO;
import com.wow.erc.booking.model.Booking;

@Service
public class BookingService {
    @Autowired
    BookingDAO bookingDAO;

    public List<Booking> getAllBookings( String poNo, String dcNo, String orderDate, String deliveryDate, String vendorNo) {
        return this.bookingDAO.findAll( poNo, dcNo, orderDate, deliveryDate, vendorNo);
    }

	/*
	 * public Booking addBooking(Booking booking) { return
	 * this.bookingDAO.save(booking); }
	 */

    //other methods omitted for brevity
}

