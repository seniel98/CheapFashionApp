package com.jdpadron98carlosmc98.cheapfashionapp.app;

import java.util.List;

public class UserData {


    private String name;
    private String email;
    private String phoneNumber;
    private List<String> likedProductList;
    private List<String> myProductsList;


    public UserData(String name, String email, String phoneNumber, List<String> likedProductList, List<String> myProductsList) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.likedProductList = likedProductList;
        this.myProductsList = myProductsList;
    }


    public List<String> getLikedProductList() {
        return likedProductList;
    }

    public void setLikedProductList(List<String> likedProductList) {
        this.likedProductList = likedProductList;
    }

    public List<String> getMyProductsList() {
        return myProductsList;
    }

    public void setMyProductsList(List<String> myProductsList) {
        this.myProductsList = myProductsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
