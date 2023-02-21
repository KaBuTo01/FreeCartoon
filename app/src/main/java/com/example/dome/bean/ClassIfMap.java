package com.example.dome.bean;

/**
 * @ClassName ClassIfMap
 * @Author name
 * @Date 2023/2/18
 * @Description
 */
public class ClassIfMap {
    private String href;
    private String name;

    public ClassIfMap( String name,String href) {
        this.href = href;
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
