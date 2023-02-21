package com.example.dome.bean;

/**
 * @ClassName ComicBean
 * @Author name
 * @Date 2023/2/14
 * @Description
 */
public class ComicBean {
    private String imageUrl;
    private String comicName;
    private String name;
    private String url;
    public ComicBean(){};

    @Override
    public String toString() {
        return "ComicBean{" +
                "imageUrl='" + imageUrl + '\'' +
                ", comicName='" + comicName + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public ComicBean(String imageUrl, String comicName, String name, String url) {
        this.imageUrl = imageUrl;
        this.comicName = comicName;
        this.name = name;
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
