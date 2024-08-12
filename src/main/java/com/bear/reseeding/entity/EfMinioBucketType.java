package com.bear.reseeding.entity;

public enum EfMinioBucketType {
    // img, video, audio, kmz,tif,model, etc.
    IMAGE, VIDEO, AUDIO, KMZ, TIF, MODEL, OTHER;

    public static EfMinioBucketType fromString(String str) {
        for (EfMinioBucketType type : EfMinioBucketType.values()) {
            if (type.toString().equalsIgnoreCase(str)) {
                return type;
            }
        }
        return OTHER;
    }


}

