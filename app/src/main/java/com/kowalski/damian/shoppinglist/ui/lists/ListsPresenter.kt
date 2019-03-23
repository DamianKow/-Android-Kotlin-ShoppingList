package com.kowalski.damian.shoppinglist.ui.lists

import org.androidannotations.annotations.EBean

@EBean
class ListsPresenter: ListsContract.Presenter {

    private lateinit var view: ListsContract.View

    override fun attach(view: ListsContract.View) {
        this.view = view
    }
}