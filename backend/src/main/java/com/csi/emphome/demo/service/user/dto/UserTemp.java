package com.csi.emphome.demo.service.user.dto;

import java.io.Serializable;
import java.sql.Date;

public class UserTemp implements Serializable {
    private int id;
    private String loginname;
    private String password;
    private int status;
    private Date createdate;
    private String username;
    private String faceurl;
    private String facepath;
    public UserTemp() {

    }
    public UserTemp(int id, String loginname, String password, int status, Date createdate, String username, String faceurl, String facepath){
        this.id = id;
        this.loginname = loginname;
        this.password = password;
        this.status = status;
        this.createdate = createdate;
        this.username = username;
        this.faceurl = faceurl;
        this.facepath = facepath;
    }
    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFaceurl() {
        return faceurl;
    }

    public void setFaceurl(String faceurl) {
        this.faceurl = faceurl;
    }

    public String getFacepath() {
        return facepath;
    }

    public void setFacepath(String facepath) {
        this.facepath = facepath;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    @Override
    public String toString() {
        return "UserTemp{" +
                "id=" + id +
                ",username='" + username + '\'' +
                ",loginname=" + loginname +
                '}';
    }
}
