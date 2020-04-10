package com.jdpadron98carlosmc98.cheapfashionapp.app;

public class ProductItem {

    public int id;
    public int drawable; //Variable para almacenar las imagenes de prueba
    public String price;
    public String name;
    public String picture;
    public UserData userData;
    public String detail;
    public Boolean liked;

    /**
     * Constructor para las imagenes de sample
     *
     * @param id
     * @param drawable
     * @param price
     * @param name
     * @param userData
     */
    public ProductItem(int id, int drawable, String price, String name, UserData userData, String detail, boolean liked) {
        this.id = id;
        this.drawable = drawable;
        this.price = price;
        this.name = name;
        this.userData = userData;
        this.detail = detail;
        this.liked = liked;
    }

    public ProductItem() {

    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
