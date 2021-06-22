package com.csi.emphome.demo.service.login.dto;

import java.io.Serializable;

public class PwdTemp implements Serializable {
    private String password;
    private String newpwd;
    private String newpassword1;
    private String username;

    public PwdTemp() {
    }

    public PwdTemp(String password, String newpwd, String newpassword1, String username) {
        this.password = password;
        this.newpwd = newpwd;
        this.newpassword1 = newpassword1;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getNewpassword1() {
        return newpassword1;
    }

    public void setNewpassword1(String newpassword1) {
        this.newpassword1 = newpassword1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "PwdTemp{" +
                "password='" + password + '\'' +
                ", newpwd='" + newpwd + '\'' +
                ", newpassword1='" + newpassword1 + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
