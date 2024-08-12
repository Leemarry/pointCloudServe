package com.bear.reseeding.entity;

import com.bear.reseeding.task.MinioService;

import javax.annotation.Resource;
import java.io.InputStream;

public enum EfMinioType {

    IMAGE {
        @Override
        public void performAction() {
            System.out.println("Performing action for IMAGE");
        }

//        @Override
//        public Boolean putObject(MinioService minioService,String objectKey, InputStream inputStream){
//            // 获取minio桶 toString()方法返回桶名
//            String bucketName = this.toString();
//            boolean isSuccess = false;
//            try{
//
//            }catch (Exception e){
//
//            }
//            return isSuccess;
//        }

        @Override
        public String toString() {
            return "efuavimage";
        }
    },
    VIDEO {
        @Override
        public void performAction() {
            System.out.println("Performing action for VIDEO");
        }
    },
    AUDIO {
        @Override
        public void performAction() {
            System.out.println("Performing action for AUDIO");
        }
    },
    KMZ {
        @Override
        public void performAction() {
            System.out.println("Performing action for KMZ");
        }
    },
    TIF {
        @Override
        public void performAction() {
            System.out.println("Performing action for TIF");
        }
    },
    MODEL {
        @Override
        public void performAction() {
            System.out.println("Performing action for MODEL");
        }
    },
    OTHER {
        @Override
        public void performAction() {
            System.out.println("Performing action for OTHER");
        }
    };

    public abstract void performAction();
}
