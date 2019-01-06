package com.kowalski.damian.shoppinglist.db.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kowalski.damian.shoppinglist.db.dao.ListDao
import com.kowalski.damian.shoppinglist.db.entities.ListEntity

@Database(entities = [(ListEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listsDao(): ListDao

}