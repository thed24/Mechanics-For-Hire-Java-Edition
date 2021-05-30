/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dom
 */
@Entity
@Table(name = "ADVERTISEMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "advertisement.findAll", query = "SELECT m FROM Advertisement m"),
    @NamedQuery(name = "advertisement.findByAdvertisementid", query = "SELECT m FROM Advertisement m WHERE m.advertisementid = :ADVERTISEMENTID")})
public class Advertisement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ADVERTISEMENTID")
    private String advertisementid;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TIMESLOT")
    private String timeSlot;
    @Column(name = "ISBOOKED")
    private Boolean isBooked;
    @Column(name = "USERID")
    private String userid;

    public Advertisement() {
    }

    public Advertisement(String advertisementid) {
        this.advertisementid = advertisementid;
    }

    public String getAdvertisementid() {
        return advertisementid;
    }

    public void setAdvertisementid(String advertisementid) {
        this.advertisementid = advertisementid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Boolean getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(Boolean isBooked) {
        this.isBooked = isBooked;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (advertisementid != null ? advertisementid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Advertisement)) {
            return false;
        }
        Advertisement other = (Advertisement) object;
        if ((this.advertisementid == null && other.advertisementid != null) || (this.advertisementid != null && !this.advertisementid.equals(other.advertisementid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Advertisement[ advertisementid=" + advertisementid + " ]";
    }
}
