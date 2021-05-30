/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.UserDTO;
import javax.mail.Authenticator;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.mail.PasswordAuthentication;
import entities.UserFacadeRemote;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dom
 */
@Named(value = "UserManagedBean")
@RequestScoped
public class UserManagedBean {

    @EJB
    private UserFacadeRemote userFacade;

    private String userid;
    private String name;
    private String password;
    private String password2;
    private String email;
    private String type;

    public UserFacadeRemote getuserFacade() {
        return userFacade;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setuserFacade(UserFacadeRemote userFacade) {
        this.userFacade = userFacade;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String phone) {
        this.type = phone;
    }

    public boolean isValidUserid(String userid) {
        return (userid != null);
    }

    public boolean isValidName(String name) {
        return (name != null);
    }

    public boolean isValidPassword(String password) {
        return (password != null);
    }

    public boolean isValidEmail(String email) {
        return (email != null);
    }

    public boolean isValidType(String type) {
        return (type != null);
    }

    public String addUser() {
        String result = "failure";
        FacesContext context = FacesContext.getCurrentInstance();
        if (isValidUserid(userid) && isValidName(name)
                && isValidPassword(password) && isValidPassword(password2)
                && isValidEmail(email) && isValidType(type)
                && password.equals(password2)) {
            UserDTO userDTO = new UserDTO(userid, name,
                    password, email, type);
            if (userFacade.createRecord(userDTO)) {
                logIn(userDTO, context);
                result = "success";
            }
        }
        return result;
    }
    
    public String validateAndLogin() {
        String result = "failure";
        FacesContext context = FacesContext.getCurrentInstance();
        UserDTO foundUser = null;
        try{
            foundUser = userFacade.getRecord(userid);
        } catch (Exception e){
            
        }
        if (isValidUserid(userid) && foundUser != null && foundUser.getPassword().equals(password)) {
            logIn(foundUser, context);
            result = "success";
        }
        return result;
    }
    
    public void logIn(UserDTO userDTO, FacesContext context){
        context.getExternalContext().getSessionMap().put("user", userDTO);
    }
    
    public String logOut(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "mainmenu?faces-redirect=true";
    }

    public String getUser() {
        String result = "failure";
        UserDTO foundUser = userFacade.getRecord(userid);
        if (isValidUserid(userid) && foundUser != null) {
            this.userid = foundUser.getUserid();
            this.name = foundUser.getName();
            this.password = foundUser.getPassword();
            this.email = foundUser.getEmail();
            this.type = foundUser.getType();
            result = "success";
        }
        return result;
    }

    @Override
    public String toString() {
        return "userid=" + userid + ", name=" + name + ", password=" + password + ", cPassword=" + password2 + ", email=" + email + ", phone=" + type + '}';
    }

    public UserManagedBean() {
    }

    private class MyAuthenticator extends Authenticator {

        PasswordAuthentication mypa;

        public MyAuthenticator(String username, String password) {
            mypa = new PasswordAuthentication(username, password);
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return mypa;
        }
    }
}
