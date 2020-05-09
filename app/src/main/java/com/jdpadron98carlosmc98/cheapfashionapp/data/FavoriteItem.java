package com.jdpadron98carlosmc98.cheapfashionapp.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.auto.value.AutoValue;

import static androidx.room.ForeignKey.CASCADE;


@Entity(
        tableName = "favorite"
        )


public class FavoriteItem {
    public FavoriteItem(String id, String uid, String pid) {
        this.id = id;
        this.uid = uid;
        this.pid = pid;
    }

    @PrimaryKey
    @NonNull
    private String id;

    private String uid;

    @ColumnInfo(name = "favoritePID")
    public String pid;


    public FavoriteItem(){
        
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
