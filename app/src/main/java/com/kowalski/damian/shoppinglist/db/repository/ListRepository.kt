package com.kowalski.damian.shoppinglist.db.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.kowalski.damian.shoppinglist.application.ShoppingListsApplication
import com.kowalski.damian.shoppinglist.db.dao.ListDao
import com.kowalski.damian.shoppinglist.db.entities.ListEntity

class ListRepository(application: Application) {

    private var listDao: ListDao
    private var allLists: LiveData<List<ListEntity>>

    init {
        var appDatabase = ShoppingListsApplication.database
        listDao = appDatabase!!.listsDao()
        allLists = listDao.getAll()
    }

    fun getAllLists(): LiveData<List<ListEntity>> {
        return allLists
    }

    fun insert(list: ListEntity) {
        InsertAsyncTask(listDao).execute(list)
    }

    fun deleteById(id: Long) {
        DeleteAsyncTask(listDao).execute(id)
    }

    inner class InsertAsyncTask(var listDao: ListDao) : AsyncTask<ListEntity, Void?, Void?>() {
        override fun doInBackground(vararg params: ListEntity): Void? {
            listDao.insert(params[0])
            return null
        }
    }

    inner class DeleteAsyncTask(var listDao: ListDao) : AsyncTask<Long, Void?, Void?>() {
        override fun doInBackground(vararg params: Long?): Void? {
            listDao.deleteListById(params[0])
            return null
        }
    }
}