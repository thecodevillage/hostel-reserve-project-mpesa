package com.thecodevillage.hostelreserve.hostel.models;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hostel")
//@NamedQueries({
//        @NamedQuery(name = "Account.findByCustomerId",query = "select a from Account a where a.customer_id= :customerId and a.id= :accountId")
//})
@Getter
@Setter
@EqualsAndHashCode
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false)
    private Long id;


    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private String location;

    private Date createdOn = new Date();


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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
