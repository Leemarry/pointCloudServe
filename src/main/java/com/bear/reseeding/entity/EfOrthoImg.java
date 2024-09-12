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
    private String mapPath;
    private String mapMd5;
    private double lat;
    private double lon;
    private String towerMark;
    private String markTag;
    private Integer startMarkNum;
    private Integer endMarkNum;

    public String getMarkTag() {
        return markTag;
    }

    public void setMarkTag(String markTag) {
        this.markTag = markTag;
    }

    public Integer getStartMarkNum() {
        return startMarkNum;
    }

    public void setStartMarkNum(Integer startMarkNum) {
        this.startMarkNum = startMarkNum;
    }

    public Integer getEndMarkNum() {
        return endMarkNum;
    }

    public void setEndMarkNum(Integer endMarkNum) {
        this.endMarkNum = endMarkNum;
    }

    public String getTowerMark() {
        return towerMark;
    }

    public void setTowerMark(String towerMark) {
        this.towerMark = towerMark;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getMapPath() {
        return mapPath;
    }

    public void setMapPath(String mapPath) {
        this.mapPath = mapPath;
    }

    public String getMapMd5() {
        return mapMd5;
    }

    public void setMapMd5(String mapMd5) {
        this.mapMd5 = mapMd5;
    }

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
