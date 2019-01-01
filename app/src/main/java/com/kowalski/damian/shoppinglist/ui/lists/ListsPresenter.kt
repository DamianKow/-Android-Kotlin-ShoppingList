package com.kowalski.damian.shoppinglist.ui.lists


class ListsPresenter: ListsContract.Presenter {

    private lateinit var view: ListsContract.View

    override fun getListsFromDb() : Array<Int> {
        TODO("not implemented")
    }

    override fun attach(view: ListsContract.View) {
        this.view = view
    }
}