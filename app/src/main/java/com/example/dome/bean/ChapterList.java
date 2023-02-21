package com.example.dome.bean;

import java.io.Serializable;

/**
 * @ClassName ChapterList
 * @Author name
 * @Date 2023/2/14
 * @Description
 */
public class ChapterList implements Serializable{
     private String url;
     private String name;

    public ChapterList() {}
    public ChapterList(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ChapterList{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
