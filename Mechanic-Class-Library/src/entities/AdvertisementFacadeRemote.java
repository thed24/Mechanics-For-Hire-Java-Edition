/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entity.AdvertisementDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Dom
 */
@Remote
public interface AdvertisementFacadeRemote {
    public boolean createRecord(AdvertisementDTO advertisementDTO);
    public AdvertisementDTO getRecord(String advertisementId);
    public ArrayList<AdvertisementDTO> getAllAdvertisements();
    public boolean updateRecord(AdvertisementDTO advertisementDTO);
    public boolean deleteRecord(AdvertisementDTO advertisementDTO);
}
