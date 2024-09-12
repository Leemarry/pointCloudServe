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
    private String markTag;
    private Integer markNum;
    private String mark; // 塔标注:杆塔号1
    private String type;  // 塔类型:杆塔2
    private String startTower; // 起始塔 3
    private String endTower; // 终止塔 4
    private String geo; // 地理位置:省 5
    private String xian; // 县 6
    private String zheng; // 镇zheng 7
    private String cun; // 镇cun 8
    private double lat; // 纬度 9
    private String latStr;
    private double lon; // 经度 10
    private String lonStr;
    private String startSpan; // 起始跨度 11
    private String endSpan; // 终止跨度 12
    private double absalt; // 绝对海拔 13
    private String absaltStr; // 绝对海拔 13
    private String ishard; // 是否硬塔 14
    private String terrain; // 地形 15
    private String topTel; // 塔顶电话 16
    private String endTel; // 塔底电话 17
    private String patrolRoute; // 巡逻路线 18
    private String advise; // 塔建议 19
    private String faultHazard; // 故障危险 20
    private String faultType; // 故障类型 21
    private String towerHazard; // 杆塔危险 22
    private String lineHazard; // 线路危险 23
    private String insulatorHazard; // 绝缘子危险 24
    private String glodHazard;// 金属危险 25
    private String groundingHazard; // 接地危险 26
    private String towerBasicHazard; // 塔基建设危险 27
    private String isCross; // 是否跨越 28
    private String crossingSituation; // 跨域情况29
    private String var1; // 预留字段 30
    private String var2; // 预留字段 31
    private String var3; // 预留字段 32
    private String var4; // 预留字段 33
    private String var5; // 预留字段 34
    private double alt;
    private double verticalLineArc; // vertical_line_arc
    private double lineLineDis;  // line_line_dis
    private double  lineTowerDis;
    private double towerRotationAngle;
    private Date createTime;
    private Date updateTime;
    private String des;
    private List<EfPhoto> photos;
    private List<EfTowerLine> towerLines;
    private EfPointCloud pointCloud;
    private EfOrthoImg orthoImg;
    private EfVideo video;

    public String getIsCross() {
        return isCross;
    }

    public void setIsCross(String isCross) {
        this.isCross = isCross;
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

    public String getMarkTag() {
        return markTag;
    }

    public void setMarkTag(String markTag) {
        this.markTag = markTag;
    }

    public Integer getMarkNum() {
        return markNum;
    }

    public void setMarkNum(Integer markNum) {
        this.markNum = markNum;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTower() {
        return startTower;
    }

    public void setStartTower(String startTower) {
        this.startTower = startTower;
    }

    public String getEndTower() {
        return endTower;
    }

    public void setEndTower(String endTower) {
        this.endTower = endTower;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public String getZheng() {
        return zheng;
    }

    public void setZheng(String zheng) {
        this.zheng = zheng;
    }

    public String getCun() {
        return cun;
    }

    public void setCun(String cun) {
        this.cun = cun;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getLatStr() {
        return latStr;
    }

    public void setLatStr(String latStr) {
        this.latStr = latStr;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getLonStr() {
        return lonStr;
    }

    public void setLonStr(String lonStr) {
        this.lonStr = lonStr;
    }

    public String getStartSpan() {
        return startSpan;
    }

    public void setStartSpan(String startSpan) {
        this.startSpan = startSpan;
    }

    public String getEndSpan() {
        return endSpan;
    }

    public void setEndSpan(String endSpan) {
        this.endSpan = endSpan;
    }

    public double getAbsalt() {
        return absalt;
    }

    public void setAbsalt(double absalt) {
        this.absalt = absalt;
    }

    public String getAbsaltStr() {
        return absaltStr;
    }

    public void setAbsaltStr(String absaltStr) {
        this.absaltStr = absaltStr;
    }

    public String getIshard() {
        return ishard;
    }

    public void setIshard(String ishard) {
        this.ishard = ishard;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getTopTel() {
        return topTel;
    }

    public void setTopTel(String topTel) {
        this.topTel = topTel;
    }

    public String getEndTel() {
        return endTel;
    }

    public void setEndTel(String endTel) {
        this.endTel = endTel;
    }

    public String getPatrolRoute() {
        return patrolRoute;
    }

    public void setPatrolRoute(String patrolRoute) {
        this.patrolRoute = patrolRoute;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    public String getFaultHazard() {
        return faultHazard;
    }

    public void setFaultHazard(String faultHazard) {
        this.faultHazard = faultHazard;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public String getTowerHazard() {
        return towerHazard;
    }

    public void setTowerHazard(String towerHazard) {
        this.towerHazard = towerHazard;
    }

    public String getLineHazard() {
        return lineHazard;
    }

    public void setLineHazard(String lineHazard) {
        this.lineHazard = lineHazard;
    }

    public String getInsulatorHazard() {
        return insulatorHazard;
    }

    public void setInsulatorHazard(String insulatorHazard) {
        this.insulatorHazard = insulatorHazard;
    }

    public String getGlodHazard() {
        return glodHazard;
    }

    public void setGlodHazard(String glodHazard) {
        this.glodHazard = glodHazard;
    }

    public String getGroundingHazard() {
        return groundingHazard;
    }

    public void setGroundingHazard(String groundingHazard) {
        this.groundingHazard = groundingHazard;
    }

    public String getTowerBasicHazard() {
        return towerBasicHazard;
    }

    public void setTowerBasicHazard(String towerBasicHazard) {
        this.towerBasicHazard = towerBasicHazard;
    }



    public String getCrossingSituation() {
        return crossingSituation;
    }

    public void setCrossingSituation(String crossingSituation) {
        this.crossingSituation = crossingSituation;
    }

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public String getVar3() {
        return var3;
    }

    public void setVar3(String var3) {
        this.var3 = var3;
    }

    public String getVar4() {
        return var4;
    }

    public void setVar4(String var4) {
        this.var4 = var4;
    }

    public String getVar5() {
        return var5;
    }

    public void setVar5(String var5) {
        this.var5 = var5;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
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

    public EfVideo getVideo() {
        return video;
    }

    public void setVideo(EfVideo video) {
        this.video = video;
    }
}
