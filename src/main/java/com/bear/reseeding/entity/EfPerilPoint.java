package com.bear.reseeding.entity;

import java.util.Date;

public class EfPerilPoint {
    private int id;
    private String mark;
    private double lng;
    private double lat;
    private double levelDis;
    private double verticalDis;
    private double clearDis;
    private  String verticalViewImg;
    private String drawingImg;
    private Integer reportId;
    private String des;
    private Date createTime;
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLevelDis() {
        return levelDis;
    }

    public void setLevelDis(double levelDis) {
        this.levelDis = levelDis;
    }

    public double getVerticalDis() {
        return verticalDis;
    }

    public void setVerticalDis(double verticalDis) {
        this.verticalDis = verticalDis;
    }

    public double getClearDis() {
        return clearDis;
    }

    public void setClearDis(double clearDis) {
        this.clearDis = clearDis;
    }

    public String getVerticalViewImg() {
        return verticalViewImg;
    }

    public void setVerticalViewImg(String verticalViewImg) {
        this.verticalViewImg = verticalViewImg;
    }

    public String getDrawingImg() {
        return drawingImg;
    }

    public void setDrawingImg(String drawingImg) {
        this.drawingImg = drawingImg;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
