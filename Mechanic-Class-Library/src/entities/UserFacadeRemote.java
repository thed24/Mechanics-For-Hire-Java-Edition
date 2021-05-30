/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entity.UserDTO;
import javax.ejb.Remote;

/**
 *
 * @author Dom
 */
@Remote
public interface UserFacadeRemote {
    public boolean createRecord(UserDTO userDTO);
    public UserDTO getRecord(String userId);
}
