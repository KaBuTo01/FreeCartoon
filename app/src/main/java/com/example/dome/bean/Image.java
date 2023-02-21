package com.example.dome.bean;

/**
 * @ClassName Image
 * @Author name
 * @Date 2023/2/12
 * @Description
 */
public class Image {
    private String imageUrl;
    private String tit;
    private String time;

    public Image() {

    }

    @Override
    public String toString() {
        return "Image{" +
                "imageUrl='" + imageUrl + '\'' +
                ", tit='" + tit + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Image(String imageUrl, String tit, String time) {
        this.imageUrl = imageUrl;
        this.tit = tit;
        this.time = time;
    }
}
