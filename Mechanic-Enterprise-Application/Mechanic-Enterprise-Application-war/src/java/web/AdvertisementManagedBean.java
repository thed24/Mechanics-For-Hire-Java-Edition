/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.AdvertisementFacadeRemote;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import entity.AdvertisementDTO;
import entity.UserDTO;
import java.util.ArrayList;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dom
 */
@Named(value = "AdvertisementManagedBean")
@RequestScoped
public class AdvertisementManagedBean {

    @EJB
    private AdvertisementFacadeRemote advertisementFacade;

    private String advertisementid;
    private String name;
    private String timeSlot;
    private Boolean isBooked;
    private String userid;

    public AdvertisementFacadeRemote getAdvertisementFacade() {
        return advertisementFacade;
    }

    public void setAdvertisementFacade(AdvertisementFacadeRemote advertisementFacade) {
        this.advertisementFacade = advertisementFacade;
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

    public boolean isValidUserid(String userid) {
        return (userid != null);
    }

    public boolean isValidName(String name) {
        return (name != null);
    }

    public boolean isValidTimeSlot(String timeSlot) {
        return (timeSlot != null);
    }

    public boolean isValidAdvertisementId(String advertisementId) {
        return (advertisementId != null);
    }
    
    public String book(){
        String result = "failure";
        if (isValidUserid(userid) && isValidName(name)
                && isValidTimeSlot(timeSlot) && isValidAdvertisementId(advertisementid)) {
            AdvertisementDTO advertisementDTO = new AdvertisementDTO(advertisementid, name,
                        timeSlot, true, userid);
            this.advertisementFacade.updateRecord(advertisementDTO);
            result = "success";
        }
        return result;
    }
    
    public String edit(){
        getAdvertisement();
        String result = "failure";
        if (isValidUserid(userid) && isValidName(name)
                && isValidTimeSlot(timeSlot) && isValidAdvertisementId(advertisementid)) {
            AdvertisementDTO advertisementDTO = new AdvertisementDTO(advertisementid, name,
                        timeSlot, isBooked, userid);
            this.advertisementFacade.updateRecord(advertisementDTO);
            result = "success";
        }
        return result;
    }

    public String delete(){
        String result = "failure";
        if (isValidUserid(userid) && isValidName(name)
                && isValidTimeSlot(timeSlot) && isValidAdvertisementId(advertisementid)) {
            AdvertisementDTO advertisementDTO = new AdvertisementDTO(advertisementid, name,
                        timeSlot, true, userid);
            this.advertisementFacade.deleteRecord(advertisementDTO);
            result = "success";
        }
        return result;
    }
        
    public String addAdvertisement() {
        String result = "failure";
        FacesContext context = FacesContext.getCurrentInstance();
        UserDTO currentUser = (UserDTO)context.getExternalContext().getSessionMap().get("user");
        if (isValidUserid(currentUser.getUserid()) && isValidName(name)
                && isValidTimeSlot(timeSlot) && isValidAdvertisementId(advertisementid)) {
            AdvertisementDTO advertisementDTO = new AdvertisementDTO(advertisementid, name,
                    timeSlot, false, currentUser.getUserid());
            if (advertisementFacade.createRecord(advertisementDTO)) {
                result = "success";
            }
        }
        return result;
    }

    public void getAdvertisement() {
        AdvertisementDTO foundAdvertisement = advertisementFacade.getRecord(advertisementid);
        this.userid = foundAdvertisement.getUserid();
        this.advertisementid = foundAdvertisement.getAdvertisementid();
        this.isBooked = foundAdvertisement.getIsBooked();
    }
    
    public ArrayList<AdvertisementDTO> getAllAdvertisements(){
        return this.advertisementFacade.getAllAdvertisements();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public AdvertisementManagedBean() {
    }
}
