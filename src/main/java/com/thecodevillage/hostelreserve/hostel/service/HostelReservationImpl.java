package com.thecodevillage.hostelreserve.hostel.service;


import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.thecodevillage.hostelreserve.hostel.models.Hostel;
import com.thecodevillage.hostelreserve.hostel.models.Room;
import com.thecodevillage.hostelreserve.hostel.models.RoomReserve;
import com.thecodevillage.hostelreserve.hostel.models.Student;
import com.thecodevillage.hostelreserve.hostel.pojo.BookingRequest;
import com.thecodevillage.hostelreserve.hostel.pojo.DownloadRequest;
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
                convertPhoneNumberToE164(bookingRequest.getMobileNumber()),
                convertPhoneNumberToE164(bookingRequest.getMobileNumber()),
                "174379",
                bookingRequest.getCallbackurl(),
                bookingRequest.getTimeouturl(),
                "Room "+room.get().getName()+room.get().getId()+"-"+reserve.getId(),
                "Reservation for "+room.get().getName());

        return new GenericResponse(200,"Request Received, Await confirmation");
    }

    @Override
    public GenericResponse downloadData() {
        List<Hostel> hostels=hostelRepository.findAllHostels();
        List<Room> rooms=roomRepository.findAllRooms();
        return new GenericResponse(200,"",new DownloadRequest(hostels,rooms));
    }


    private Optional<Student> findStudentByAdmissionNumber(String admNo){
        return studentRepository.findByAdmissionNumber(admNo);
    }

    @Override
    public Long getHostelCount() {
        return hostelRepository.count();
    }


    public static String convertPhoneNumberToE164(String ph_no) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber pn = null;
        String temp_ph_no = null;
        try {
            pn = phoneNumberUtil.parse(ph_no, "KE");
            temp_ph_no = phoneNumberUtil.format(pn, PhoneNumberUtil.PhoneNumberFormat.E164);
            temp_ph_no = temp_ph_no.replaceAll("\\+","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp_ph_no;
    }
}
