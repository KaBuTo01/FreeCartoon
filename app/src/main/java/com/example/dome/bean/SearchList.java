package com.example.dome.bean;

/**
 * @ClassName SearchList
 * @Author name
 * @Date 2023/2/15
 * @Description
 */
public class SearchList {
    private String ImageUrl;
    private String name;
    private String url;
    private String text1;
    private String text2;
    private String text3;
    public SearchList(){}
    public SearchList(String imageUrl, String name, String url, String text1, String text2, String text3) {
        this.ImageUrl = imageUrl;
        this.name = name;
        this.url = url;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
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

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }
}
