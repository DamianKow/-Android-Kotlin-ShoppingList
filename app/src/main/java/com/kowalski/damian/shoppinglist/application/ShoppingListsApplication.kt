package com.kowalski.damian.shoppinglist.application

import android.app.Application
import android.arch.persistence.room.Room
import com.kowalski.damian.shoppinglist.db.room.AppDatabase

class ShoppingListsApplication : Application() {
    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        ShoppingListsApplication.database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "shopping-list-db"
        ).build()
    }
}