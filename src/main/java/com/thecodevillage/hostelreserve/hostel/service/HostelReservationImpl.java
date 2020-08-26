package com.thecodevillage.hostelreserve.hostel.service;


import com.thecodevillage.hostelreserve.hostel.models.Hostel;
import com.thecodevillage.hostelreserve.hostel.models.Room;
import com.thecodevillage.hostelreserve.hostel.models.RoomReserve;
import com.thecodevillage.hostelreserve.hostel.models.Student;
import com.thecodevillage.hostelreserve.hostel.pojo.BookingRequest;
import com.thecodevillage.hostelreserve.hostel.pojo.GenericResponse;
import com.thecodevillage.hostelreserve.hostel.repository.*;
import com.thecodevillage.hostelreserve.mpesa.service.MpesaService;
import com.thecodevillage.hostelreserve.mpesa.service.MpesaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostelReservationImpl implements HostelReservation{

    private static final Logger log = LoggerFactory.getLogger(HostelReservationImpl.class);

    private HostelRepository hostelRepository;
    private PaymentRepository paymentRepository;
    private RoomRepository roomRepository;
    private RoomReserveRepository roomReserveRepository;
    private StudentRepository studentRepository;

    MpesaService mpesaService;


    @Autowired
    public HostelReservationImpl(HostelRepository hostelRepository,
                                 PaymentRepository paymentRepository,
                                 RoomRepository roomRepository,
                                 RoomReserveRepository roomReserveRepository,
                                 StudentRepository studentRepository,MpesaService mpesaService) {
        this.hostelRepository = hostelRepository;
        this.paymentRepository = paymentRepository;
        this.roomRepository = roomRepository;
        this.roomReserveRepository = roomReserveRepository;
        this.studentRepository = studentRepository;
        this.mpesaService = mpesaService;
    }

    @Override
    public GenericResponse getAllHostels() {
        return new GenericResponse(200,"ok",hostelRepository.findAllHostels());
    }

    @Override
    public GenericResponse getAllRooms() {
        return new GenericResponse(200,"ok",roomRepository.findAllRooms());
    }

    @Override
    public GenericResponse getHostelById(Long id) {

        Optional<Hostel> hostel=hostelRepository.findById(id);
        return hostel.map(hostel1 -> new GenericResponse(200, "ok", hostel1)).orElseGet(() -> new GenericResponse(201, "NOT Found"));

    }

    @Override
    public GenericResponse getRoomsByHostelId(Long hostelId) {
        List<Room> rooms=roomRepository.findRoomsByHostelId(hostelId);
        if (rooms.size() > 0)
            return new GenericResponse(200,"ok",rooms);
        return new GenericResponse(201,"no data found");
    }

    @Override
    public GenericResponse createBooking(BookingRequest bookingRequest) {
        Optional<Student> student=findStudentByAdmissionNumber(bookingRequest.getStudentNumber());

        if (!student.isPresent())
            return new GenericResponse(201,"Invalid Reg No.");

        Optional<Room> room=roomRepository.findById(bookingRequest.getRoomId());

        if (!room.isPresent())
            return new GenericResponse(201,"Invalid Room No.");

        RoomReserve reserve=new RoomReserve();
        reserve.setAmountPaid(bookingRequest.getAmount());
        reserve.setPaid(false);
        reserve.setRoom(room.get());
        reserve.setStudent(student.get());
        reserve.setEmail(student.get().getEmail());
        roomReserveRepository.save(reserve);
        log.info("Init mpesa req ");
        mpesaService.STKPushSimulation("174379",
                "MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMTcwODI0MTU1MDU1",
                "20170824155055",
                "CustomerPayBillOnline",
                "1",
                bookingRequest.getMobileNumber(),
                bookingRequest.getMobileNumber(),
                "174379",
                bookingRequest.getCallbackurl(),
                bookingRequest.getTimeouturl(),
                room.get().getId()+"",
                "Reservation for "+room.get().getName());

        return new GenericResponse(200,"Request Received, Await confirmation");
    }


    private Optional<Student> findStudentByAdmissionNumber(String admNo){
        return studentRepository.findByAdmissionNumber(admNo);
    }

    @Override
    public Long getHostelCount() {
        return hostelRepository.count();
    }
}
