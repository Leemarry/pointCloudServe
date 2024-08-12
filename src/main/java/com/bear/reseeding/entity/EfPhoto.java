package com.bear.reseeding.entity;

import java.util.Date;

public class EfPhoto {
    private int id;
    private String mark;
    private Date createTime;
    private double size;
    private double sizeScale;
    private String path;
    private String pathScale;
    private String imageTag;
    private String deviceId;
    private Integer type;
    private Integer towerId;
    private String formats;

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getSizeScale() {
        return sizeScale;
    }

    public void setSizeScale(double sizeScale) {
        this.sizeScale = sizeScale;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathScale() {
        return pathScale;
    }

    public void setPathScale(String pathScale) {
        this.pathScale = pathScale;
    }

    public String getImageTag() {
        return imageTag;
    }

    public void setImageTag(String imageTag) {
        this.imageTag = imageTag;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTowerId() {
        return towerId;
    }

    public void setTowerId(Integer towerId) {
        this.towerId = towerId;
    }
}
