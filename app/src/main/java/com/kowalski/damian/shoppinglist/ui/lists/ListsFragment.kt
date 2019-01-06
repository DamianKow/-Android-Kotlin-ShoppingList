package com.kowalski.damian.shoppinglist.ui.lists

import android.app.AlertDialog
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.kowalski.damian.shoppinglist.R
import com.kowalski.damian.shoppinglist.db.entities.ListEntity
import com.kowalski.damian.shoppinglist.db.viewmodel.ListViewModel
import com.kowalski.damian.shoppinglist.utils.GuiUtils
import kotlinx.android.synthetic.main.fragment_lists.*
import org.androidannotations.annotations.*


@EFragment(R.layout.fragment_lists)
@OptionsMenu(R.menu.list_fragment_menu)
class ListsFragment : Fragment(), ListsContract.View {

    private lateinit var adapter: ListsAdapter
    private lateinit var listViewModel: ListViewModel

    var deleteAllMenuItem: MenuItem? = null

    @Bean(ListsPresenter::class)
    lateinit var presenter: ListsContract.Presenter

    @Bean
    lateinit var guiUtils: GuiUtils

    @AfterInject
    fun afterInject() {
        presenter.attach(this)
    }

    @AfterViews
    fun afterViews() {
        setupRecyclerView()
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        progressbar_list.visibility = View.VISIBLE
        empty_list_layout.visibility = View.GONE
        lists_recycler.visibility = View.GONE

    }

    @Click(R.id.add_new_list)
    fun createConfirmationDialog() {
        val dialog = createDialog()
        dialog.show()
    }

    @OptionsItem(R.id.delete_all_action)
    fun deleteAllList() {
        val builder = AlertDialog.Builder(context) //TODO zamienić na guiUtils
        builder.setTitle("Usuwanie")
        builder.setMessage("Czy napewno chcesz usunąć wszystkie listy zakupów?")
        builder.setPositiveButton(context?.getString(R.string.yes)) { dialog, which ->
            deleteAll()
            dialog.dismiss()
        }
        builder.setNegativeButton(context?.getString(R.string.no)) { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        deleteAllMenuItem = menu!!.findItem(R.id.delete_all_action)
        listViewModel.getAllLists().observe(this, Observer<List<ListEntity>> { list ->
            adapter.updateDataSet(list!!)
            checkIfEmptyList(list)
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun deleteAll() {
        listViewModel.deleteAll()
    }

    fun addNewList(listName: String) {
        listViewModel.insert(ListEntity(0, listName))
    }

    private fun checkIfEmptyList(list: List<ListEntity>?) {
        progressbar_list.visibility = View.GONE
        if (list != null && !list.isEmpty()) {
            empty_list_layout.visibility = View.GONE
            lists_recycler.visibility = View.VISIBLE
            deleteAllMenuItem?.isVisible = true
        } else {
            empty_list_layout.visibility = View.VISIBLE
            lists_recycler.visibility = View.GONE
            deleteAllMenuItem?.isVisible = false
        }
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
        val builder = AlertDialog.Builder(context) //TODO zamienić na guiUtils
        builder.setTitle("Usuwanie")
        builder.setMessage("Czy napewno chcesz usunąć liste zakupów?")
        builder.setPositiveButton(context?.getString(R.string.yes)) { dialog, which ->
            deleteList(list.id)
            dialog.dismiss()
        }
        builder.setNegativeButton(context?.getString(R.string.no)) { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun deleteList(id: Long) {
        listViewModel.deleteById(id)
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
