package com.jdpadron98carlosmc98.cheapfashionapp.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.jdpadron98carlosmc98.cheapfashionapp.data.UserData;

public class Converters {

    @TypeConverter
    public String UserToString(UserData userData) {
        return new Gson().toJson(userData);
    }

    @TypeConverter
    public UserData StringToUser(String userString) {
        return new Gson().fromJson(userString, UserData.class);
    }

}
