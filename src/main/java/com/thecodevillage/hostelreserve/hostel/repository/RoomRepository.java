package com.thecodevillage.hostelreserve.hostel.repository;

import com.thecodevillage.hostelreserve.hostel.models.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends PagingAndSortingRepository<Room,Long> {

    @Query("select a from Room a")
    List<Room> findAllRooms();

    //@Query("select a from Room a")
    List<Room> findRoomsByHostelId(@Param("hostelId") Long hostelId);
}
