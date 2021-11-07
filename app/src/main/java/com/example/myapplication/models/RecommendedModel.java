package com.example.myapplication.models;

public class RecommendedModel {
    String name;
    int price;
    String rating;
    String description;
    String img_url;

    public RecommendedModel(){ }

    public RecommendedModel(String name, int price, String rating, String description, String img_url) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
