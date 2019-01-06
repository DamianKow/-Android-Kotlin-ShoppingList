package com.kowalski.damian.shoppinglist.ui.lists

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.kowalski.damian.shoppinglist.R
import com.kowalski.damian.shoppinglist.model.ListDB

class ListsAdapter(private val listener: (ListDB) -> Unit, private val deleteListener: (ListDB) -> Unit) :
    RecyclerView.Adapter<ListsAdapter.ListsViewHolder>() {

    private val items = mutableListOf<ListDB>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.single_item_list, parent, false)
        return ListsViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListsViewHolder, position: Int) {
        if (itemCount > position) {
            val listDB = items[position]
            holder.bindData(listDB.name)
            holder.deleteImageView.setOnClickListener { deleteListener.invoke(listDB) }
            holder.listItem.setOnClickListener { listener.invoke(listDB) }
        }
    }

    fun updateDataSet(newItems: List<ListDB>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ListsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var listNameTextView: TextView = itemView.findViewById(R.id.list_name)
        var deleteImageView: ImageView = itemView.findViewById(R.id.list_delete)
        var listItem: LinearLayout = itemView.findViewById(R.id.list_item)

        fun bindData(listName: String) {
            listNameTextView.text = listName
        }
    }
}