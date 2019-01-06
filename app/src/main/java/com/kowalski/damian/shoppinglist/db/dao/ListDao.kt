package com.kowalski.damian.shoppinglist.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.kowalski.damian.shoppinglist.db.entities.ListEntity

@Dao
interface ListDao {

    @Query("SELECT * FROM lists")
    fun getAll(): LiveData<List<ListEntity>>

    @Insert
    fun insert(vararg list: ListEntity)

    @Delete
    fun delete(list: ListEntity)

    @Query("DELETE FROM lists WHERE id = :listId")
    fun deleteListById(listId: Long?)
}