package com.example.foodeat.Domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private int id;
    private String title;
    private String pic;
    private String description;
    private Double fee;
    private int numberInCart;

    private int id_category;

    public FoodDomain() {
    }

    public FoodDomain(String title, String pic, String description, Double fee,int id_category) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.id_category = id_category;
    }

    public FoodDomain(String title, String pic, String description, Double fee, int numberInCart,int id_category) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.numberInCart = numberInCart;
        this.id_category = id_category;
    }

    public FoodDomain(int id, String title, String pic, String description, Double fee, int numberInCart,int id_cateigory) {
        this.id = id;
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.numberInCart = numberInCart;
        this.id_category = id_category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }
}

