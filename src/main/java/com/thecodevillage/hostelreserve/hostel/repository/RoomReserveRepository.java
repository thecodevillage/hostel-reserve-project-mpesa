package com.thecodevillage.hostelreserve.hostel.repository;

import com.thecodevillage.hostelreserve.hostel.models.RoomReserve;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomReserveRepository extends PagingAndSortingRepository<RoomReserve,Long> {
}
