package com.csi.emphome.demo.domain.test;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="test_inf")
public class TestItem {
    @Id
    private int id;
    private String info;
    private Date time;

    public TestItem() {
    }

    public TestItem(int id, String info, Date time) {
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
        return "ListItem{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", time=" + time +
                '}';
    }
}
