package com.thecodevillage.hostelreserve.hostel.pojo;


import com.thecodevillage.hostelreserve.hostel.models.Hostel;
import com.thecodevillage.hostelreserve.hostel.models.Room;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class DownloadRequest {


    private List<Hostel> hostels;

    private List<Room> rooms;

    public DownloadRequest() {

    }

    public List<Hostel> getHostels() {
        return hostels;
    }

    public void setHostels(List<Hostel> hostels) {
        this.hostels = hostels;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public DownloadRequest(List<Hostel> hostels, List<Room> rooms) {
        this.hostels = hostels;
        this.rooms = rooms;
    }
}
