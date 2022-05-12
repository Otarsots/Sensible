package com.example.sensible.data

import android.content.Context
import androidx.room.*
import com.example.sensible.models.Nutriments
import com.example.sensible.models.Product
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken

object NutrimentsConverter{
    @TypeConverter
    @JvmStatic
    fun toUserInfo(nutriments : String): Nutriments {
        val type=object : TypeToken<Nutriments>(){}.type
        return Gson().fromJson(nutriments,type)
    }
    @JvmStatic
    @TypeConverter
    fun toUserInfoJson(nutriments : Nutriments):String{
        val type=object :TypeToken<Nutriments>(){}.type
        return Gson().toJson(nutriments,type).toString()
    }
}



@Database(entities = [(Product::class)], version = 1)
@TypeConverters(NutrimentsConverter::class)
abstract class ProductRoomDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {

        private var INSTANCE: ProductRoomDatabase? = null

        fun getInstance(context: Context): ProductRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductRoomDatabase::class.java,
                        "product_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}