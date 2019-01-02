package com.kowalski.damian.shoppinglist.ui.lists

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import com.kowalski.damian.shoppinglist.R
import com.kowalski.damian.shoppinglist.model.ListDB
import kotlinx.android.synthetic.main.fragment_lists.*
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EFragment

@EFragment(R.layout.fragment_lists)
class ListsFragment: Fragment(), ListsContract.View {

    @Bean(ListsPresenter::class)
    lateinit var presenter: ListsContract.Presenter

    private lateinit var adapter: ListsAdapter
    private var tmpList: List<ListDB> = listOf(ListDB(1, "Auchan"), ListDB(2, "test"))

    @AfterInject
    fun afterInject() {
        presenter.attach(this)
    }

    @AfterViews
    fun afterViews() {
        setupRecyclerView()
        this.adapter.updateDataSet(tmpList)
    }

    private fun setupRecyclerView() {
        this.adapter = ListsAdapter { list -> onListClicked(list) }
        lists_recycler.adapter = this.adapter
        lists_recycler.layoutManager = LinearLayoutManager(context)
    }

    private fun onListClicked(list: ListDB) {
        //not implemented yet
    }
}
