
package com.marketApp.dataBases;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
//KULLANICILAR
public class Users  {
    @Id
    @Column(name = "USERNAME")
    private String userName;

    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "SURNAME")
    private String sName;

    public Users() {
    }
    //constructor
        public Users (String userName, String password, String name, String sName) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.sName = sName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    
    
}
