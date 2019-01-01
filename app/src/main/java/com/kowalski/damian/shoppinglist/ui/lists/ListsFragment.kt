package com.kowalski.damian.shoppinglist.ui.lists

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kowalski.damian.shoppinglist.R
import org.androidannotations.annotations.Bean

class ListsFragment: Fragment(), ListsContract.View {

    @Bean(ListsPresenter::class)
    private lateinit var presenter: ListsContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_lists, container, false)
        presenter.attach(this)
        return view
    }
}
