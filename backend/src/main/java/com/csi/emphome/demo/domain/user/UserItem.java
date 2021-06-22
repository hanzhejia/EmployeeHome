package com.csi.emphome.demo.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "user_inf")
public class UserItem {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "Loginname")
    private  String loginname;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "STATUS")
    private int status;
    @Column(name = "Createdate")
    private Date createdate;
    @Column(name = "Username")
    private String username;
    @Column(name = "Faceurl")
    private String faceurl;
    @Column(name = "Facepath")
    private String facepath;



    public UserItem() {

    }
    public UserItem(int id, String loginname, String password, int status, Date createdate, String username, String faceurl, String facepath){
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

}
