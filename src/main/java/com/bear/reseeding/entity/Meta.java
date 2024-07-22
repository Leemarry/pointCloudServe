package com.bear.reseeding.entity;



public class Meta {

    private String host; // 主机地址
    private String keywords; // 关键字
    private String description; // 介绍

    // 省略get/set/toString方法

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // tostring

    @Override
    public String toString() {
        return "Meta{" +
                "host='" + host + '\'' +
                ", keywords='" + keywords + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}