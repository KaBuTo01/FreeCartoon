package com.example.dome.bean;

/**
 * @ClassName Banner
 * @Author name
 * @Date 2023/2/14
 * @Description
 */
public class BannerBean {
    private String ImageUrl;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public BannerBean(){};

    public BannerBean(String imageUrl, String url, String title) {
        ImageUrl = imageUrl;
        this.url = url;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "ImageUrl='" + ImageUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
