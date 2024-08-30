package com.bear.reseeding.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class EfTower implements Serializable {
    private static final long serialVersionUID = -62790280969449409L;
    /**
     * 主键自增，任务编号
     */
    private Integer id;
    private String name;
    private String mark;
    private String type;
    private double span;
    private String terrain;
    private double lat;
    private double lon;
    private double alt;
    private double absalt;
    private double verticalLineArc;
    private double lineLineDis;
    private double     lineTowerDis;
    private double towerRotationAngle;
    private Date createTime;
    private Date updateTime;
    private String des;
    private List<EfPhoto> photos;
    private List<EfTowerLine> towerLines;
    private EfPointCloud pointCloud;
    private EfOrthoImg orthoImg;
    private EfVideo video;
    private String geo;

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public EfVideo getVideo() {
        return video;
    }

    public void setVideo(EfVideo video) {
        this.video = video;
    }

    public List<EfTowerLine> getTowerLines() {
        return towerLines;
    }

    public void setTowerLines(List<EfTowerLine> towerLines) {
        this.towerLines = towerLines;
    }

    public EfPointCloud getPointCloud() {
        return pointCloud;
    }

    public void setPointCloud(EfPointCloud pointCloud) {
        this.pointCloud = pointCloud;
    }

    public EfOrthoImg getOrthoImg() {
        return orthoImg;
    }

    public void setOrthoImg(EfOrthoImg orthoImg) {
        this.orthoImg = orthoImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSpan() {
        return span;
    }

    public void setSpan(double span) {
        this.span = span;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
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

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    public double getAbsalt() {
        return absalt;
    }

    public void setAbsalt(double absalt) {
        this.absalt = absalt;
    }

    public double getVerticalLineArc() {
        return verticalLineArc;
    }

    public void setVerticalLineArc(double verticalLineArc) {
        this.verticalLineArc = verticalLineArc;
    }

    public double getLineLineDis() {
        return lineLineDis;
    }

    public void setLineLineDis(double lineLineDis) {
        this.lineLineDis = lineLineDis;
    }

    public double getLineTowerDis() {
        return lineTowerDis;
    }

    public void setLineTowerDis(double lineTowerDis) {
        this.lineTowerDis = lineTowerDis;
    }

    public double getTowerRotationAngle() {
        return towerRotationAngle;
    }

    public void setTowerRotationAngle(double towerRotationAngle) {
        this.towerRotationAngle = towerRotationAngle;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<EfPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<EfPhoto> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "EfTower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark='" + mark + '\'' +
                ", type='" + type + '\'' +
                ", span=" + span +
                ", terrain='" + terrain + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", alt=" + alt +
                ", absalt=" + absalt +
                ", verticalLineArc=" + verticalLineArc +
                ", lineLineDis=" + lineLineDis +
                ", lineTowerDis=" + lineTowerDis +
                ", towerRotationAngle=" + towerRotationAngle +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", des='" + des + '\'' +
                ", photos=" + photos +
                ", towerLines=" + towerLines +
                ", pointCloud=" + pointCloud +
                ", orthoImg=" + orthoImg +
                ", video=" + video +
                ", geo='" + geo + '\'' +
                '}';
    }

}
