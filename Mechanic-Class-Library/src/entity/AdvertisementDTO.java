/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

public class AdvertisementDTO implements Serializable {
    private String advertisementid;
    private final String name;
    private final String timeSlot;
    private final Boolean isBooked;
    private final String userid;

    public AdvertisementDTO(String advertisementid, String name, String timeSlot, Boolean isBooked, String userid) {
        this.advertisementid = advertisementid;
        this.name = name;
        this.timeSlot = timeSlot;
        this.isBooked = isBooked;
        this.userid = userid;
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

    public String getTimeSlot() {
        return timeSlot;
    }

    public Boolean getIsBooked() {
        return isBooked;
    }

    public String getUserid() {
        return userid;
    }
}
