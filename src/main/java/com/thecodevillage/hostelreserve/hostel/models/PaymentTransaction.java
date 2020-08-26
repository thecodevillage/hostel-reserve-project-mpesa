package com.thecodevillage.hostelreserve.hostel.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "payment_transactions")
@Getter
@Setter
@EqualsAndHashCode
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false)
    private Long id;

    private String reference;

    private boolean paid = false;

    private double amount;

    private String mobileNumber;


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


    private String status = "PENDING";


    private Date createdOn = new Date();

}