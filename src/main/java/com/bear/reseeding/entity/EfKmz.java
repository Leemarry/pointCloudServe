package com.bear.reseeding.entity;

import java.util.Date;

public class EfKmz {
    private int id;
    private String kmzName;
    private String kmzPath;
    private long kmzSize;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKmzName() {
        return kmzName;
    }

    public void setKmzName(String kmzName) {
        this.kmzName = kmzName;
    }

    public String getKmzPath() {
        return kmzPath;
    }

    public void setKmzPath(String kmzPath) {
        this.kmzPath = kmzPath;
    }

    public long getKmzSize() {
        return kmzSize;
    }

    public void setKmzSize(long kmzSize) {
        this.kmzSize = kmzSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
