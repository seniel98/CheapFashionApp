package com.jdpadron98carlosmc98.cheapfashionapp.app;

import android.content.Context;

import java.util.ArrayList;

public class Repository implements Contract {

    private static Repository INSTANCE;
    private Context context;

    private ArrayList<String> gi50List = new ArrayList<>();
    private ArrayList<String> cell_lineList = new ArrayList<>();
    private ArrayList<String> id_expList = new ArrayList<>();
    private ArrayList<String> restList = new ArrayList<>();


    public static Contract getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(context);
        }
        return INSTANCE;
    }

    private Repository(Context context) {

        this.context = context;
    }
}
