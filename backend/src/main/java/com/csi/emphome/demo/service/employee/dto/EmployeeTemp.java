package com.csi.emphome.demo.service.employee.dto;

import java.io.Serializable;
import java.sql.Date;

public class EmployeeTemp implements Serializable {
    private int id;
    private String info;
    private Date time;

    public EmployeeTemp() {
    }

    public EmployeeTemp(int id, String info, Date time) {
        this.id = id;
        this.info = info;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TestTemp{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", time=" + time +
                '}';
    }
}
