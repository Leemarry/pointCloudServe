package com.bear.reseeding.entity;

public enum EfUploadType {
    IMAGE {
        @Override
        public String toString() {
            return "Image";
        }
    },
    PHOTO {
        @Override
        public String toString() {
            return "Photo";
        }
    },
    VIDEO {
        @Override
        public String toString() {
            return "Video";
        }
    },
    AUDIO {
        @Override
        public String toString() {
            return "Audio";
        }
    },
    OTHER {
        @Override
        public String toString() {
            return "Other";
        }
    };
    public abstract String toString();


}
