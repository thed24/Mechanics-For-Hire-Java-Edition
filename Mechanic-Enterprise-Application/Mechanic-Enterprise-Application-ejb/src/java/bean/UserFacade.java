/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.UserFacadeRemote;

@Stateless
public class UserFacade implements UserFacadeRemote, UserFacadeLocal {

    @PersistenceContext(unitName = "Mechanic")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
     return em;
    }
    private void create(User user) {
     em.persist(user);
    }
    private void edit(User user) {
     em.merge(user);
    }
    private void remove(User user) {
     em.remove(em.merge(user));
    }
    private User find(Object id) {
     return em.find(User.class, id);
    }
    
    private User myDTO2DAO(UserDTO userDTO) {
        User user = new User();
        user.setUserid(userDTO.getUserid());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setType(userDTO.getType());
        return user;
   }

    @Override
    public boolean createRecord(UserDTO userDTO) {
        if (find(userDTO.getUserid()) != null) {
        // user whose userid can be found
        return false;
       }
       // user whose userid could not be found
       try {
        User user = this.myDTO2DAO(userDTO);
        this.create(user); // add to database
        return true;
       } catch (Exception ex) {
        return false; // something is wrong, should not be here though
       }
    }
    
    public UserDTO myDAO2DTO(User user) {
        return new UserDTO(
                user.getUserid(),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getType()
        );
    }

    @Override
    public UserDTO getRecord(String userId) {
        return this.myDAO2DTO(this.find(userId));
    }
    
    public ArrayList<UserDTO> getAllUsers() {
        List<User> allUsers = (List<User>) em.createNamedQuery("user.findAll").getResultList();
        ArrayList userAsDto = new ArrayList<User>();
        allUsers.forEach((user) -> {
            userAsDto.add(this.myDAO2DTO(user));
        });
        return userAsDto;
    }
}
