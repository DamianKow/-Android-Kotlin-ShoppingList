package com.kowalski.damian.shoppinglist.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.kowalski.damian.shoppinglist.db.entities.ListEntity
import com.kowalski.damian.shoppinglist.db.entities.ProductsEntity

@Dao
interface ProductsDao {

    @Query("SELECT * FROM products WHERE list_id = :listId")
    fun getAllByListId(listId: Long?): LiveData<List<ProductsEntity>>

    @Insert
    fun insert(vararg product: ProductsEntity, listId: Long)

    @Query("DELETE FROM products WHERE list_id = :listId")
    fun deleteAllByListId(listId: Long?)

    @Query("DELETE FROM lists WHERE id = :productId")
    fun deleteProductById(productId: Long?)
}