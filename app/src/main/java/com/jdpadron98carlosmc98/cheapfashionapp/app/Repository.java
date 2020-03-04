package com.jdpadron98carlosmc98.cheapfashionapp.app;

import android.content.Context;

public class Repository implements Contract {

    private static Repository INSTANCE;
    private Context context;


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
