package com.kowalski.damian.shoppinglist.ui.lists

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.kowalski.damian.shoppinglist.R
import com.kowalski.damian.shoppinglist.db.entities.ListEntity
import com.kowalski.damian.shoppinglist.db.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_lists.*
import org.androidannotations.annotations.*


@EFragment(R.layout.fragment_lists)
class ListsFragment: Fragment(), ListsContract.View {

    @Bean(ListsPresenter::class)
    lateinit var presenter: ListsContract.Presenter

    private lateinit var adapter: ListsAdapter

    private lateinit var listViewModel: ListViewModel

    @AfterInject
    fun afterInject() {
        presenter.attach(this)
    }

    @AfterViews
    fun afterViews() {
        setupRecyclerView()
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        listViewModel.getAllLists().observe(this, Observer<List<ListEntity>> { list ->
            adapter.updateDataSet(list!!)
        })
    }

    @Click(R.id.add_new_list)
    fun createConfirmationDialog() {
        var dialog = createDialog()
        dialog.show()
    }

    private fun setupRecyclerView() {
        this.adapter = ListsAdapter({ list -> onListClicked(list) }, { list -> onListDeleted(list) })
        lists_recycler.adapter = this.adapter
        lists_recycler.layoutManager = LinearLayoutManager(context)
    }

    private fun onListClicked(list: ListEntity) {
        //not implemented yet
        Toast.makeText(context, list.id.toString(), Toast.LENGTH_SHORT).show() //TODO: efekt clickable
    }

    private fun onListDeleted(list: ListEntity) {
        listViewModel.deleteById(list.id)
    }

    fun addNewList(listName: String) {
        listViewModel.insert(ListEntity(0, listName))
    }

    private fun createDialog(): Dialog {
        var dialog: Dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_confimation)
        var listName: EditText = dialog.findViewById(R.id.list_name_edtxt)
        var addButton: Button = dialog.findViewById(R.id.add_list_button_dialog)

        addButton.setOnClickListener {
            var name = listName.text
            if (checkData(name.toString())) {
                addNewList(name.toString())
                dialog.dismiss()
            }
        }
        return dialog
    }

    private fun checkData(name: String?): Boolean {
        return name != null && !name.isEmpty()
    }
}
