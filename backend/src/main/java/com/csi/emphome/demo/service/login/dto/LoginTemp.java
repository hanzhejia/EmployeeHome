package com.csi.emphome.demo.service.login.dto;

public class LoginTemp {
    private String username;
    private String password;

    public LoginTemp() {
    }

    public LoginTemp(String loginname, String password) {
        this.username = loginname;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginTemp{" +
                "loginname='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
