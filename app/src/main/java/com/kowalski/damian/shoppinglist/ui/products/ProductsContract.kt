package com.kowalski.damian.shoppinglist.ui.products

import com.kowalski.damian.shoppinglist.ui.BaseContract

interface ProductsContract {

    interface View : BaseContract.BaseView

    interface Presenter : BaseContract.BasePresenter<ProductsContract.View>
}