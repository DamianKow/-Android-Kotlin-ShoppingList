package com.kowalski.damian.shoppinglist.ui.lists

import com.kowalski.damian.shoppinglist.ui.BaseContract

interface ListsContract {

    interface View : BaseContract.BaseView{


    }

    interface Presenter : BaseContract.BasePresenter<ListsContract.View> {

        fun getListsFromDb() : Array<Int>
    }
}