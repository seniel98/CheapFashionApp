package com.jdpadron98carlosmc98.cheapfashionapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;

import java.util.List;

@Dao
public interface ProductDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(ProductItem productItem);

    @Delete
    void deleteProduct(ProductItem category);

    @Query("SELECT * FROM products")
    List<ProductItem> loadProducts();
}
