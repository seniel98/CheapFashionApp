package com.jdpadron98carlosmc98.cheapfashionapp.data;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "products")

public class ProductItem {

    @NonNull
    @PrimaryKey
    public String pid;

    public String price;
    public String name;
    public String picture;
    @Ignore
    public ImageView imageView;
    public UserData userData;
    public String detail;

    public ProductItem(String pid, String price, String name, String picture, String detail, UserData userData) {
        this.pid = pid;
        this.picture = picture;
        this.price = price;
        this.name = name;
        this.userData = userData;
        this.detail = detail;
    }

    public ProductItem() {

    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
