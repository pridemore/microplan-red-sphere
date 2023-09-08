package com.example.microplanredsphereandroid.models;

public class ImageUploadItem {
    String fileName;
    String imageBase64;
    boolean isBase64;

    public ImageUploadItem(String fileName, String imageBase64, boolean isBase64) {
        this.fileName = fileName;
        this.imageBase64 = imageBase64;
        this.isBase64 = isBase64;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public boolean isBase64() {
        return isBase64;
    }

    public void setBase64(boolean base64) {
        isBase64 = base64;
    }
}
