package com.jdpadron98carlosmc98.cheapfashionapp.app;

import android.content.Context;

import com.jdpadron98carlosmc98.cheapfashionapp.R;

import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryContract {

    private static Repository INSTANCE;
    private Context context;


    private String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.";


    private List<ProductItem> productItemList = new ArrayList<>();
    private UserData userData1 = new UserData("name1", "email1", "phone1");
    private UserData userData2 = new UserData("name2", "email2", "phone2");
    private UserData userData3 = new UserData("name3", "email3", "phone3");
    private UserData userData4 = new UserData("name4", "email4", "phone4");
    private UserData userData5 = new UserData("name5", "email5", "phone5");
    private UserData userData6 = new UserData("name6", "email6", "phone6");

    private ProductItem productItem1 = new ProductItem(0, R.drawable.cloth1, "35.00",
            "Cloth 1", userData1, loremIpsum,false);
    private ProductItem productItem2 = new ProductItem(1, R.drawable.cloth2, "36.00",
            "Cloth 2", userData2, loremIpsum,false);
    private ProductItem productItem3 = new ProductItem(2, R.drawable.cloth3, "37.00",
            "Cloth 3", userData3, loremIpsum,false);
    private ProductItem productItem4 = new ProductItem(3, R.drawable.cloth4, "38.00",
            "Cloth 4", userData4, loremIpsum,false);
    private ProductItem productItem5 = new ProductItem(4, R.drawable.cloth5, "39.00",
            "Cloth 5", userData5, loremIpsum,false);
    private ProductItem productItem6 = new ProductItem(5, R.drawable.cloth6, "40.00",
            "Cloth 6", userData6, loremIpsum,false);


    public static RepositoryContract getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(context);
        }
        return INSTANCE;
    }


    private Repository(Context context) {
        this.context = context;
        productItemList.add(productItem1);
        productItemList.add(productItem2);
        productItemList.add(productItem3);
        productItemList.add(productItem4);
        productItemList.add(productItem5);
        productItemList.add(productItem6);
    }


    @Override
    public List<ProductItem> getProductList() {
        return productItemList;
    }
}
