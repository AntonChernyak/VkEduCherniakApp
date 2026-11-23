package com.example.vkeducherniakapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vkeducherniakapp.data.db.entity.AppDetailsEntity

@Database(
    entities = [AppDetailsEntity::class],
    version = 1,
)
@TypeConverters(CategoryConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDetailsDao(): AppDetailsDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }

}