/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String userid;
    private String name;
    private final String password;
    private final String email;
    private final String type;

    public UserDTO(String userid, String name, String password, String email, String type) {
        this.userid = userid;
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public void setName(String recordName) {
        this.name = recordName;
    }

    public void setUserid(String recordId) {
        this.userid = recordId;
    }
}
