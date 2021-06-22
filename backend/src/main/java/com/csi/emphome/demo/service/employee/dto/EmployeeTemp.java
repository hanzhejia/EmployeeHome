package com.csi.emphome.demo.service.employee.dto;

import java.io.Serializable;
import java.sql.Date;

public class EmployeeTemp implements Serializable {

    private int id;
    private int deptid;
    private int jobid;
    private String name;
    private String card;
    private String address;
    private String post;
    private String tel;
    private String phone;
    private String qqnum;
    private String email;
    private int sex;
    private String party;
    private java.util.Date birthday;
    private String race;
    private String education;
    private String speciality;
    private String hobby;
    private String remark;
    private java.util.Date createdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeptid() {
        return deptid;
    }

    public void setDeptid(int deptid) {
        this.deptid = deptid;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQqnum() {
        return qqnum;
    }

    public void setQqnum(String qqnum) {
        this.qqnum = qqnum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public java.util.Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(java.util.Date createdate) {
        this.createdate = createdate;
    }

    public EmployeeTemp(int deptid, int jobid, String name, String card, String address, String phone, String email, int sex, String education, Date createdate) {
        this.deptid = deptid;
        this.jobid = jobid;
        this.name = name;
        this.card = card;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.education = education;
        this.createdate = createdate;
    }

    public EmployeeTemp(String name, String card, int sex, int jobid, String education, String email,
                        String tel, String phone, String party, String qqnum, String address, String post, java.util.Date birthday, String race,
                        String speciality, String hobby, String remark, int deptid){
        this.id = Integer.parseInt(card.substring(15, 18));
        this.deptid = deptid;
        this.jobid = jobid;
        this.name = name;
        this.card = card;
        this.address = address;
        this.post = post;
        this.tel = tel;
        this.phone = phone;
        this.qqnum = qqnum;
        this.email = email;
        this.sex = sex;
        this.party = party;
        this.birthday = birthday;
        this.race = race;
        this.education = education;
        this.speciality = speciality;
        this.hobby = hobby;
        this.remark = remark;
        this.createdate = new java.util.Date();
    }

    @Override
    public String toString() {
        return "EmployeeTemp{" +
                "ID=" + id +
                ", DEPT_ID=" + deptid +
                ", JOB_ID=" + jobid +
                ", NAME='" + name + '\'' +
                ", CARD_ID='" + card + '\'' +
                ", ADDRESS='" + address + '\'' +
                ", POST_CODE='" + post + '\'' +
                ", TEL='" + tel + '\'' +
                ", PHONE='" + phone + '\'' +
                ", QQ_NUM='" + qqnum + '\'' +
                ", EMAIL='" + email + '\'' +
                ", SEX=" + sex +
                ", PARTY='" + party + '\'' +
                ", BIRTHDAY=" + birthday +
                ", RACE='" + race + '\'' +
                ", EDUCTION='" + education + '\'' +
                ", SPECIALITY='" + speciality + '\'' +
                ", HOBBY='" + hobby + '\'' +
                ", REMARK='" + remark + '\'' +
                ", CREATE_DATE=" + createdate +
                '}';
    }

    public EmployeeTemp() {
    }




}
