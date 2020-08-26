package com.thecodevillage.hostelreserve.hostel.repository;

import com.thecodevillage.hostelreserve.hostel.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends PagingAndSortingRepository<Student,Long> {
    @Query("select a from Student a")
    List<Student> findAllStudents();


    Optional<Student> findByAdmissionNumber(String admissionNumber);


}
