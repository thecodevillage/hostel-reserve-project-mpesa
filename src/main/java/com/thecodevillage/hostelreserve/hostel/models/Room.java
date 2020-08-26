package com.thecodevillage.hostelreserve.hostel.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rooms")
//@NamedQueries({
//        @NamedQuery(name = "Account.findByCustomerId",query = "select a from Account a where a.customer_id= :customerId and a.id= :accountId")
//})
@Getter
@Setter
@EqualsAndHashCode
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false)
    private Long id;


    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;


    private int maxOccupants;


    private double cost;


    private Date createdOn = new Date();


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(
            name = "hostel_id"
    )
    private Hostel hostel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxOccupants() {
        return maxOccupants;
    }

    public void setMaxOccupants(int maxOccupants) {
        this.maxOccupants = maxOccupants;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }
}
