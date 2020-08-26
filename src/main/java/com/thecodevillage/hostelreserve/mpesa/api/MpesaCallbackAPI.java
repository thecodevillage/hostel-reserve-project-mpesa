package com.thecodevillage.hostelreserve.mpesa.api;

import com.thecodevillage.hostelreserve.hostel.pojo.BookingRequest;
import com.thecodevillage.hostelreserve.hostel.pojo.GenericResponse;
import com.thecodevillage.hostelreserve.hostel.service.HostelReservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/callback")
public class MpesaCallbackAPI {
    private HostelReservation hostelReservation;


    private static final Logger log = LoggerFactory.getLogger(MpesaCallbackAPI.class);

    @Autowired
    public MpesaCallbackAPI(HostelReservation hostelReservation) {
        this.hostelReservation = hostelReservation;
    }




    @PostMapping(value = "/c2b")
    public ResponseEntity c2bCallback(RequestEntity<String> requestEntity){
        log.info(requestEntity.getBody());
        return new ResponseEntity<>("", HttpStatus.OK);
    }


}
