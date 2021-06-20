package com.csi.emphome.demo.domain.face;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "facekey_inf")
public class Face {
    @Id
    private int id;
    private String base64;

    public Face() {
    }

    public Face(int id, String base64) {
        this.id = id;
        this.base64 = base64;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "Face{" +
                "id=" + id +
                ", base64='" + base64 + '\'' +
                '}';
    }
}
