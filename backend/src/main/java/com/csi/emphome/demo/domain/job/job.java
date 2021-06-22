package com.csi.emphome.demo.domain.job;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="job_inf")
public class job {
    @Id
    private int ID;
    private String NAME;
    private String REMAKE;

    public job() {
    }

    public job(int ID, String NAME, String REMAKE) {
        this.ID = ID;
        this.NAME = NAME;
        this.REMAKE = REMAKE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getREMAKE() {
        return REMAKE;
    }

    public void setREMAKE(String REMAKE) {
        this.REMAKE = REMAKE;
    }

    @Override
    public String toString() {
        return "job{" +
                "ID=" + ID +
                ", NAME='" + NAME + '\'' +
                ", REMAKE='" + REMAKE + '\'' +
                '}';
    }
}
