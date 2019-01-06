package com.kowalski.damian.shoppinglist.db.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.kowalski.damian.shoppinglist.db.entities.ListEntity
import com.kowalski.damian.shoppinglist.db.repository.ListRepository

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private var listRepository: ListRepository = ListRepository(application)
    private var allLists: LiveData<List<ListEntity>>

    init {
        allLists = listRepository.getAllLists()
    }

    fun getAllLists(): LiveData<List<ListEntity>> {
        return allLists
    }

    fun insert(listEntity: ListEntity) {
        listRepository.insert(listEntity)
    }

    fun deleteById(id: Long) {
        listRepository.deleteById(id)
    }

    fun deleteAll() {
        listRepository.deleteAll()
    }
}