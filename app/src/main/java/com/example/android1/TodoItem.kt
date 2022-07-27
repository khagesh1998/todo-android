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
}

class TodoItem(private var dataSet: List<Todo>):RecyclerView.Adapter<ViewHolder>() {
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
        if(dataSet[position].isChecked){
            viewHolder.textView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            viewHolder.textView.setTextColor(Color.parseColor("#023020"))
        }else{
            viewHolder.textView.paintFlags = Paint.ANTI_ALIAS_FLAG
            viewHolder.textView.setTextColor(Color.RED)
        }
        viewHolder.checkBox.isChecked = dataSet[position].isChecked
        viewHolder.button.setOnClickListener {
            dataSet[position].isChecked = !dataSet[position].isChecked
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = dataSet.size
}