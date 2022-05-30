package com.universl.danumaiwasanawai.model;

import com.google.gson.annotations.SerializedName;

public class Posts {

    private String article_id;
    private String article_title;
    private String article_img;
    private String article_description;

    public String getArticle_img() {
        return article_img;
    }

    public void setArticle_img(String article_img) {
        this.article_img = article_img;
    }

    public String getArticle_description() {
        return article_description;
    }

    public void setArticle_description(String article_description) {
        this.article_description = article_description;
    }

    public Posts(String article_id, String article_title) {
        this.article_id = article_id;
        this.article_title = article_title;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_title() {
        return article_title;
    }
}
