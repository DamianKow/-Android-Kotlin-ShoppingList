package com.kowalski.damian.shoppinglist.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products", foreignKeys = arrayOf((ForeignKey(entity = ListEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("list_id"),
    onDelete = ForeignKey.CASCADE))))
data class ProductsEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "list_id") var listId: Long
    )