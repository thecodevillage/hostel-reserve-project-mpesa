package com.thecodevillage.hostelreserve;

import com.thecodevillage.hostelreserve.hostel.models.Hostel;
import com.thecodevillage.hostelreserve.hostel.models.Room;
import com.thecodevillage.hostelreserve.hostel.models.Student;
import com.thecodevillage.hostelreserve.hostel.repository.HostelRepository;
import com.thecodevillage.hostelreserve.hostel.repository.RoomRepository;
import com.thecodevillage.hostelreserve.hostel.repository.StudentRepository;
import com.thecodevillage.hostelreserve.hostel.service.HostelReservation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HostelreserveApplication {


	public static void main(String[] args) {
		SpringApplication.run(HostelreserveApplication.class, args);
	}


	@Bean
	public CommandLineRunner setupDefaultUser(HostelReservation hostelReservation, HostelRepository hostelRepository, RoomRepository roomRepository, StudentRepository studentRepository) {
		return args -> {
			if (hostelReservation.getHostelCount() == 0) {

				Hostel hostel=new Hostel();
				hostel.setCode("1001");
				hostel.setName("Lion");
				hostel.setLocation("Kiambu Road");

				hostelRepository.save(hostel);


				Room room=new Room();
				room.setCode("2001");
				room.setName("1A");
				room.setCost(5000);
				room.setHostel(hostel);
				room.setMaxOccupants(5);
				roomRepository.save(room);


				Student student=new Student();
				student.setRoom(room);
				student.setAdmissionNumber("5456");
				student.setEmail("stude@gmail.com");
				student.setFullName("Student");
				student.setIdNumber("4535345");
				student.setMobileNumber("254722520441");
				studentRepository.save(student);
			}

		};

	}
}
