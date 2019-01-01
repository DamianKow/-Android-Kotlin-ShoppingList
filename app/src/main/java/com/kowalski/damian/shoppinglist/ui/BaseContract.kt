package com.kowalski.damian.shoppinglist.ui

interface BaseContract {

    interface BasePresenter<in T> {

        fun attach(view: T)
    }

    interface BaseView {

    }
}