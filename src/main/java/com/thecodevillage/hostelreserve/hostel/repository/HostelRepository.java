package com.thecodevillage.hostelreserve.hostel.repository;

import com.thecodevillage.hostelreserve.hostel.models.Hostel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HostelRepository extends PagingAndSortingRepository<Hostel,Long> {

    @Query("select a from Hostel a")
    List<Hostel> findAllHostels();
}
