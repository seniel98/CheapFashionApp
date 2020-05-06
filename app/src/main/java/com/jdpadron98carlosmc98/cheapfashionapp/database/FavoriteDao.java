package com.jdpadron98carlosmc98.cheapfashionapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jdpadron98carlosmc98.cheapfashionapp.data.FavoriteItem;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteProduct(FavoriteItem item);

    @Delete
    void deleteFavoriteProduct(FavoriteItem category);

    @Query("SELECT * FROM favorite")
    List<FavoriteItem> loadFavoriteProducts();
}
