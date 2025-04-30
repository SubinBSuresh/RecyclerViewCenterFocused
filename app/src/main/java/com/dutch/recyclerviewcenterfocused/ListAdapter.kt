package com.dutch.recyclerviewcenterfocused

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val items: List<String>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.setBackgroundResource(R.drawable.item_background)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.text.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(android.R.id.text1)
    }


}
