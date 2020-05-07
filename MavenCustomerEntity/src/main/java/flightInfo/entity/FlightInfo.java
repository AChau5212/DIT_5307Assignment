/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightInfo.entity;

import admin.entity.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;

/**
 *
 * @author ICT-Student
 */
@Entity
@NamedQueries({
@NamedQuery(name = "findAllUser", query = "SELECT s FROM Staff s"),
@NamedQuery(name = "findUser", query = "SELECT s from Staff s WHERE s.username = :username AND s.password = :password" )})


public class FlightInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightNo;
    @Size(min=1)
    private String Destination;
    
    @Temporal(TemporalType.DATE)
    private Date flightDate;
    @Temporal(TemporalType.TIME)
    private Date flightTime;

    public Long getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(Long flightNo) {
        this.flightNo = flightNo;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Date getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Date flightTime) {
        this.flightTime = flightTime;
    }
    


    
    



//    public int getFlightNo() {
//        return flightNo;
//    }
//
//    public void setFlightNo(int flightNo) {
//        this.flightNo = flightNo;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//    
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getMobilePhone() {
//        return mobilePhone;
//    }
//
//
//    public void setMobilePhone(String mobilePhone) {
//        this.mobilePhone = mobilePhone;
//    }
//    
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightNo != null ? flightNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FlightInfo)) {
            return false;
        }
        FlightInfo other = (FlightInfo) object;
        if ((this.flightNo == null && other.flightNo != null) || (this.flightNo != null && !this.flightNo.equals(other.flightNo))) {
            return false;
        }
        return true;
    }

    
}
