/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.AdvertisementFacadeRemote;
import entity.AdvertisementDTO;
import entity.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dom
 */
@Stateless
public class AdvertisementFacade implements AdvertisementFacadeRemote, AdvertisementFacadeLocal {

    @PersistenceContext(unitName = "Mechanic")
    private EntityManager em;

    private void create(Advertisement advertisement) {
     em.persist(advertisement);
    }
    private void edit(Advertisement advertisement) {
     em.merge(advertisement);
    }
    private void remove(Advertisement advertisement) {
     em.remove(em.merge(advertisement));
    }
    private Advertisement find(Object id) {
     return em.find(Advertisement.class, id);
    }
    
    @Override
    public boolean createRecord(AdvertisementDTO advertisementDTO) {
       if (find(advertisementDTO.getAdvertisementid()) != null) {
        return false;
       }
       try {
        Advertisement advertisement = this.myDTO2DAO(advertisementDTO);
        this.create(advertisement);
        return true;
       } catch (Exception ex) {
        return false;
       }    
    }

    @Override
    public AdvertisementDTO getRecord(String advertisementId) {
        return this.myDAO2DTO(this.find(advertisementId));
    }
    
    @Override
    public boolean updateRecord(AdvertisementDTO advertisementDTO) {
        String advertisementId = advertisementDTO.getAdvertisementid();
        Advertisement advertisement = this.myDTO2DAO(advertisementDTO);
        this.edit(advertisement);
        Advertisement newAdvertisement = this.find(advertisementId);
        return true;
    }
    
    @Override
    public boolean deleteRecord(AdvertisementDTO advertisementDTO) {
        Advertisement advertisement = this.myDTO2DAO(advertisementDTO);
        this.remove(advertisement);
        return true;
    }
    
    public AdvertisementDTO myDAO2DTO(Advertisement advertisement) {
        return new AdvertisementDTO(
            advertisement.getAdvertisementid(),
            advertisement.getName(),
            advertisement.getTimeSlot(),
            advertisement.getIsBooked(),
            advertisement.getUserid()
        );
    }
    
    public Advertisement myDTO2DAO(AdvertisementDTO advertisementDTO) {
        Advertisement advertisement = new Advertisement();
        advertisement.setUserid(advertisementDTO.getUserid());
        advertisement.setName(advertisementDTO.getName());
        advertisement.setIsBooked(advertisementDTO.getIsBooked());
        advertisement.setAdvertisementid(advertisementDTO.getAdvertisementid());
        advertisement.setTimeSlot(advertisementDTO.getTimeSlot());
        return advertisement;
    }
    
    @Override
    public ArrayList<AdvertisementDTO> getAllAdvertisements() {
        List<Advertisement> allAdvertisements = (List<Advertisement>) em.createNamedQuery("advertisement.findAll").getResultList();
        ArrayList advertisementAsDto = new ArrayList<Advertisement>();
        allAdvertisements.forEach((advertisement) -> {
            advertisementAsDto.add(this.myDAO2DTO(advertisement));
        });
        return advertisementAsDto;
    }
}
