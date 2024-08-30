package com.bear.reseeding.entity;

import java.util.Date;

public class EfReport {
    private Integer id;
    private String mark;
    private String path;
    private double size;
    private  int type;
    private Date createTime;
    private String formats;
    private String towerMark;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "EfReport{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", type=" + type +
                ", createTime=" + createTime +
                ", formats='" + formats + '\'' +
                ", towerMark='" + towerMark + '\'' +
                '}';
    }
}
