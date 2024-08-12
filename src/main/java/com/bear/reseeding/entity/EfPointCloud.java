package com.bear.reseeding.entity;

import java.util.Date;

public class EfPointCloud {
    private int id;
    private String mark;
    private int towerId;
    private String originType;
    private double originSize;
    private String originCloudUrl;
    private String amendType;
    private String amendCloudUrl;
    private double amendSize;
    private String webUrl;
    private double webSize;
    private String formats;
    private Date createTime;
    private Date updateTime;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public double getWebSize() {
        return webSize;
    }

    public void setWebSize(double webSize) {
        this.webSize = webSize;
    }

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

    public int getTowerId() {
        return towerId;
    }

    public void setTowerId(int towerId) {
        this.towerId = towerId;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public double getOriginSize() {
        return originSize;
    }

    public void setOriginSize(double originSize) {
        this.originSize = originSize;
    }

    public String getOriginCloudUrl() {
        return originCloudUrl;
    }

    public void setOriginCloudUrl(String originCloudUrl) {
        this.originCloudUrl = originCloudUrl;
    }

    public String getAmendType() {
        return amendType;
    }

    public void setAmendType(String amendType) {
        this.amendType = amendType;
    }

    public String getAmendCloudUrl() {
        return amendCloudUrl;
    }

    public void setAmendCloudUrl(String amendCloudUrl) {
        this.amendCloudUrl = amendCloudUrl;
    }

    public double getAmendSize() {
        return amendSize;
    }

    public void setAmendSize(double amendSize) {
        this.amendSize = amendSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "EfPointCloud{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", towerId=" + towerId +
                ", originType='" + originType + '\'' +
                ", originSize=" + originSize +
                ", originCloudUrl='" + originCloudUrl + '\'' +
                ", amendType='" + amendType + '\'' +
                ", amendCloudUrl='" + amendCloudUrl + '\'' +
                ", amendSize=" + amendSize +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
