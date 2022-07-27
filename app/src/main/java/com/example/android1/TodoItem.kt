package com.example.android1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoItem(private var dataSet: List<Todo>):RecyclerView.Adapter<TodoItem.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.list_item_title)
        }
    }

    fun updateData(dataSet: List<Todo>) {
        this.dataSet = dataSet
        notifyItemInserted(this.dataSet.size-1)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position].title
        Log.d("ABCABC", "Position: $position")
    }

    override fun getItemCount() = dataSet.size
}