package com.thecodevillage.hostelreserve.hostel.api;

import com.thecodevillage.hostelreserve.hostel.pojo.BookingRequest;
import com.thecodevillage.hostelreserve.hostel.pojo.GenericResponse;
import com.thecodevillage.hostelreserve.hostel.service.HostelReservation;
import com.thecodevillage.hostelreserve.mpesa.service.MpesaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class HostelReservationAPI {
    private HostelReservation hostelReservation;


    private static final Logger log = LoggerFactory.getLogger(HostelReservationAPI.class);

    @Autowired
    public HostelReservationAPI(HostelReservation hostelReservation) {
        this.hostelReservation = hostelReservation;
    }


    @GetMapping(value = "/hostels/all")
    public ResponseEntity getAllHostels(){
        GenericResponse genericResponse=hostelReservation.getAllHostels();
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/rooms")
    public ResponseEntity getRooms(){
        GenericResponse genericResponse=hostelReservation.getAllRooms();
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }


    @PostMapping(value = "/booking/create")
    public ResponseEntity createAccount(@RequestBody BookingRequest bookingRequest){
        log.info(bookingRequest.toString());
        GenericResponse booking=hostelReservation.createBooking(bookingRequest);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

//    @PostMapping(value = "/update")
//    public ResponseEntity updateCustomer(@RequestBody Customer customer){
//        System.out.println("Update Customer in DB Called# ");
//        return new ResponseEntity<>(bankService.updateCustomer(customer), HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/update/bulk",method = RequestMethod.POST)
//    public ResponseEntity updateCustomerBulk(@RequestBody CustomerUploadReq customerUploadReq){
//        System.out.println("Update Customer in DB Called# ");
//        return new ResponseEntity<>(bankService.updateCustomerBulk(customerUploadReq), HttpStatus.OK);
//    }

}
