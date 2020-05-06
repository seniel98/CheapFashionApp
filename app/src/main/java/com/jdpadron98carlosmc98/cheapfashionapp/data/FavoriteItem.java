package com.jdpadron98carlosmc98.cheapfashionapp.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;


@Entity(
        tableName = "favorite",
        foreignKeys = @ForeignKey(
                entity = ProductItem.class,
                parentColumns = "pid",
                childColumns = "favoritePID",
                onDelete = CASCADE
        )
)

public class FavoriteItem {
    @PrimaryKey
    @NonNull
    private String uid;

    @ColumnInfo(name = "favoritePID")
    public String id;

    public FavoriteItem(@NonNull String uid, String id) {
        this.uid = uid;
        this.id = id;
    }

    public FavoriteItem(){
        
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
