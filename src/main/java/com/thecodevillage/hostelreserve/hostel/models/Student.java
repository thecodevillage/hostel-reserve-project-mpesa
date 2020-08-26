package com.thecodevillage.hostelreserve.hostel.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
//@NamedQueries({
//        @NamedQuery(name = "Account.findByCustomerId",query = "select a from Account a where a.customer_id= :customerId and a.id= :accountId")
//})
@Getter
@Setter
@EqualsAndHashCode
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String admissionNumber;


    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String idNumber;


    @Column(nullable = false)
    private String email;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "room_id"
    )
    private Room room;


    private Date createdOn = new Date();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
