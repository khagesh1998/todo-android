package com.example.android1

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.listItemTitle)
    val button: Button = view.findViewById(R.id.listItemButton)
    val checkBox: CheckBox = view.findViewById(R.id.checkBox)

    fun bind(onClick: (position: Int) -> Unit) {
        button.setOnClickListener {
            onClick(adapterPosition)
        }
    }
}

class TodoItem() : RecyclerView.Adapter<ViewHolder>() {
    private var dataSet: MutableList<Todo> = mutableListOf()

    fun addOnTop(todo: Todo) {
        this.dataSet.add(0,todo)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position].title
        if (dataSet[position].isChecked) {
            viewHolder.textView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            viewHolder.textView.setTextColor(Color.parseColor("#023020"))
        } else {
            viewHolder.textView.paintFlags = Paint.ANTI_ALIAS_FLAG
            viewHolder.textView.setTextColor(Color.RED)
        }
        viewHolder.checkBox.isChecked = dataSet[position].isChecked
        viewHolder.bind { p ->
            dataSet[p].isChecked = !dataSet[p].isChecked
            notifyItemChanged(p)
        }
    }

    override fun getItemCount() = dataSet.size
}