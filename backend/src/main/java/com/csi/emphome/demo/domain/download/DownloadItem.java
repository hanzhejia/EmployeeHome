package com.csi.emphome.demo.domain.download;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="local_storage")
public class DownloadItem {
    @Id
    @Column(name = "storage_id")
    private int storageId;
    @Column(name = "real_name")
    private String realName;
    private String name;
    private String suffix;
    private String path;
    private String type;
    private String size;

    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "create_time")
    private Timestamp createTime;
    @Column(name = "update_time")
    private Timestamp updateTime;

    private String detail;

    public DownloadItem() {
    }

    public DownloadItem(int storageId, String realName, String name, String suffix, String path, String type, String size, String createBy, String updateBy, Timestamp createTime, Timestamp updateTime, String detail) {
        this.storageId = storageId;
        this.realName = realName;
        this.name = name;
        this.suffix = suffix;
        this.path = path;
        this.type = type;
        this.size = size;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.detail = detail;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String desc) {
        this.detail = desc;
    }

    @Override
    public String toString() {
        return "DownloadItem{" +
                "storageId=" + storageId +
                ", realName='" + realName + '\'' +
                ", name='" + name + '\'' +
                ", suffix='" + suffix + '\'' +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", detail='" + detail + '\'' +
                '}';
    }
}
