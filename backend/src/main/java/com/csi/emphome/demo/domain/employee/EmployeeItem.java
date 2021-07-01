package com.csi.emphome.demo.domain.employee;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name="employee_inf")
public class EmployeeItem {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "DEPT_ID")
    private int deptid;
    @Column(name = "JOB_ID")
    private int jobid;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CARD_ID")
    private String card;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "POST_CODE")
    private String postcode;
    @Column(name = "TEL")
    private String tel;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "QQ_NUM")
    private String qqnum;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "SEX")
    private int sex;
    @Column(name = "PARTY")
    private String party;
    @Column(name = "BIRTHDAY")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;
    @Column(name = "RACE")
    private String race;
    @Column(name = "EDUCATION")
    private String education;
    @Column(name = "SPECIALITY")
    private String speciality;
    @Column(name = "HOBBY")
    private String hobby;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "CREATE_DATE")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdate;

    public EmployeeItem() {
    }



    public EmployeeItem(int id ,String name, String card, int sex, int jobid, String education, String email,
                        String tel, String phone, String party, String qqnum, String address, String postcode, Date birthday, String race,
                        String speciality, String hobby, String remark, int deptid){
        this.id = id;
        this.deptid = deptid;
        this.jobid = jobid;
        this.name = name;
        this.card = card;
        this.address = address;
        this.postcode = postcode;
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
        this.createdate = new Date();
    }
    public int getId() {
        return id;
    }

    public void setId(int ID) {
        this.id = ID;
    }

    public int getDeptid() {
        return deptid;
    }

    public void setDeptid(int DEPT_ID) {
        this.deptid = DEPT_ID;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int JOB_ID) {
        this.jobid = JOB_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String NAME) {
        this.name = NAME;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String CARD_ID) {
        this.card = CARD_ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String ADDRESS) {
        this.address = ADDRESS;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String POST_CODE) {
        this.postcode = POST_CODE;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String TEL) {
        this.tel = TEL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String PHONE) {
        this.phone = PHONE;
    }

    public String getQqnum() {
        return qqnum;
    }

    public void setQqnum(String QQ_NUM) {
        this.qqnum = QQ_NUM;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String EMAIL) {
        this.email = EMAIL;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int SEX) {
        this.sex = SEX;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String PARTY) {
        this.party = PARTY;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date BIRTHDAY) {
        this.birthday = BIRTHDAY;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String RACE) {
        this.race = RACE;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String EDUCTION) {
        this.education = EDUCTION;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String SPECIALITY) {
        this.speciality = SPECIALITY;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String HOBBY) {
        this.hobby = HOBBY;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String REMARK) {
        this.remark = REMARK;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date CREATE_DATE) {
        this.createdate = CREATE_DATE;
    }

    @Override
    public String toString() {
        return "EmployeeItem{" +
                "ID=" + id +
                ", DEPT_ID=" + deptid +
                ", JOB_ID=" + jobid +
                ", NAME='" + name + '\'' +
                ", CARD_ID='" + card + '\'' +
                ", ADDRESS='" + address + '\'' +
                ", POST_CODE='" + postcode + '\'' +
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

    public EmployeeItem(int deptid, int jobid, String name, String card, String phone, int sex) {
        this.deptid = deptid;
        this.jobid = jobid;
        this.name = name;
        this.card = card;
        this.phone = phone;
        this.sex = sex;
    }
}
