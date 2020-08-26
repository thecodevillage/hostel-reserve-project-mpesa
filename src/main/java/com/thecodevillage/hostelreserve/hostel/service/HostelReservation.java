package com.thecodevillage.hostelreserve.hostel.service;

import com.thecodevillage.hostelreserve.hostel.pojo.BookingRequest;
import com.thecodevillage.hostelreserve.hostel.pojo.GenericResponse;

public interface HostelReservation {


    GenericResponse getAllHostels();

    GenericResponse getAllRooms();

    GenericResponse getHostelById(Long id);


    GenericResponse getRoomsByHostelId(Long hostelId);


    GenericResponse createBooking(BookingRequest bookingRequest);


    Long getHostelCount();


}
