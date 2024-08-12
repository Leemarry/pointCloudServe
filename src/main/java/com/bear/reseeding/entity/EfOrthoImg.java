package com.bear.reseeding.entity;

import java.util.Date;

public class EfOrthoImg {
    private Integer id;
    private String mark;
    private String path;
    private double size;
    private String des;
    private Date createTime;
    private double scaleSize;
    private String scalePath;
    private String formats;

    public double getScaleSize() {
        return scaleSize;
    }

    public void setScaleSize(double scaleSize) {
        this.scaleSize = scaleSize;
    }

    public String getScalePath() {
        return scalePath;
    }

    public void setScalePath(String scalePath) {
        this.scalePath = scalePath;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
