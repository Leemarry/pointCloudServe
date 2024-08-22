package com.bear.reseeding.entity;

import java.util.Date;

public class EfVideo {
private int id;
private String mark;
private String path;
private double size;
private int type;
private String deviceId;
private String deviceName;
private Integer towerId;
private String towerMark;
private Date createTime;
private String formats;

    public String getTowerMark() {
        return towerMark;
    }

    public void setTowerMark(String towerMark) {
        this.towerMark = towerMark;
    }

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getTowerId() {
        return towerId;
    }

    public void setTowerId(Integer towerId) {
        this.towerId = towerId;
    }

    @Override
    public String toString() {
        return "EfVideo{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", path='" + path + '\'' +
                ", type=" + type +
                ", deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", towerId=" + towerId +
                '}';
    }
}
