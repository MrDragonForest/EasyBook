package com.dragonforest.easybook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dragonforest.easybook.database.dao.BlogDao
import com.dragonforest.easybook.database.entity.BlogEntity

/**
 *
 * create by DragonForest at 2020/10/25
 */
@Database(
    entities = [BlogEntity::class],
    version = 1
)
abstract class EasyBookDatabase : RoomDatabase() {
    abstract fun blogDao(): BlogDao

    companion object {
        private val databaseName = "easy_book_database"
        private var instance: EasyBookDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): EasyBookDatabase? {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context,
                            EasyBookDatabase::class.java,
                            "$databaseName.db"
                        ).allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .fallbackToDestructiveMigrationOnDowngrade()
                            .setJournalMode(JournalMode.AUTOMATIC)
                            .enableMultiInstanceInvalidation().build()
                    }
                }
            }
            return instance
        }
    }


}