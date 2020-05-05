package com.jdpadron98carlosmc98.cheapfashionapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;

@Database(entities = {ProductItem.class},
        version = 1)
@TypeConverters({Converters.class})
public abstract class CatalogDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

}

