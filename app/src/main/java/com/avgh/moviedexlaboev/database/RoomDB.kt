package com.avgh.moviedexlaboev.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.avgh.moviedexlaboev.database.dao.MovieDao
import com.avgh.moviedexlaboev.database.entity.Movie


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class RoomDB: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(appContext: Context): RoomDB {
            if (INSTANCE == null) {
                synchronized(RoomDB::class) {
                    INSTANCE = Room
                        .databaseBuilder(appContext.applicationContext
                            , RoomDB::class.java
                            ,"movieDex_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}