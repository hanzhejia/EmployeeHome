package com.csi.emphome.demo.service.download.dto;

import java.io.Serializable;
import java.sql.Date;
import java.lang.Integer;
import java.sql.Timestamp;

public class DownloadTemp implements Serializable {
    private int storageId;
    private String realName;
    private String name;
    private String createTime;
    private String createBy;
    private String detail;

    public DownloadTemp() {
    }

    public DownloadTemp(int storageId, String realName, String name, String createTime, String createBy, String detail) {
        this.storageId = storageId;
        this.realName = realName;
        this.name = name;
        this.createTime = createTime;
        this.createBy = createBy;
        this.detail = detail;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }
    public Timestamp getCreateTimeByTS() {
        return new Timestamp(Long.parseLong(createTime));
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "DownloadTemp{" +
                "storageId=" + storageId +
                ", realName='" + realName + '\'' +
                ", name='" + name + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createBy='" + createBy + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
