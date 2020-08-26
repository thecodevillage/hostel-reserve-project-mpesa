package com.thecodevillage.hostelreserve.hostel.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "room_reservation")
//@NamedQueries({
//        @NamedQuery(name = "Account.findByCustomerId",query = "select a from Account a where a.customer_id= :customerId and a.id= :accountId")
//})
@Getter
@Setter
@EqualsAndHashCode
public class RoomReserve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private boolean paid;


    private double amountPaid;

    private double balance;

    @Column(nullable = false)
    private String email;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id"
    )
    private Student student;


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

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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