package com.bear.reseeding.entity;

import java.util.Date;
import java.util.List;

public class EfTowerLine {
    private int id;
    private String mark;
    private String startTowerMark;
    private String endTowerMark;
    private double lineLength;
    private Date createTime;
    private String des;
    private List<EfPhoto> startphotos;
    private List<EfPhoto> endphotos;

    public List<EfPhoto> getStartphotos() {
        return startphotos;
    }

    public void setStartphotos(List<EfPhoto> startphotos) {
        this.startphotos = startphotos;
    }

    public List<EfPhoto> getEndphotos() {
        return endphotos;
    }

    public void setEndphotos(List<EfPhoto> endphotos) {
        this.endphotos = endphotos;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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

    public String getStartTowerMark() {
        return startTowerMark;
    }

    public void setStartTowerMark(String startTowerMark) {
        this.startTowerMark = startTowerMark;
    }

    public String getEndTowerMark() {
        return endTowerMark;
    }

    public void setEndTowerMark(String endTowerMark) {
        this.endTowerMark = endTowerMark;
    }

    public double getLineLength() {
        return lineLength;
    }

    public void setLineLength(double lineLength) {
        this.lineLength = lineLength;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
