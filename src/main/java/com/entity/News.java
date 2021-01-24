package com.entity;

import java.io.Serializable;

public class News implements Serializable {
    private Integer id;
    private String title;
    private String content;

    public News(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public News() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
