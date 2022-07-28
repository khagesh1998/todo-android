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
    private val textView: TextView = view.findViewById(R.id.listItemTitle)
    private val button: Button = view.findViewById(R.id.listItemButton)
    private val checkBox: CheckBox = view.findViewById(R.id.checkBox)

    fun update(todo: Todo){
        textView.text = todo.title
        if (todo.isChecked) {
            textView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            textView.setTextColor(Color.parseColor("#023020"))
        } else {
            textView.paintFlags = Paint.ANTI_ALIAS_FLAG
            textView.setTextColor(Color.RED)
        }
        checkBox.isChecked = todo.isChecked
    }

    fun onClick(onClickCallback: (position: Int) -> Unit) {
        button.setOnClickListener {
            onClickCallback(adapterPosition)
        }
    }

    fun onLongClick(onClickCallback: (position: Int) -> Unit){
        button.setOnLongClickListener{
            onClickCallback(adapterPosition)
            true
        }
    }
}

class TodoItem() : RecyclerView.Adapter<ViewHolder>() {
    private var dataSet: MutableList<Todo> = mutableListOf()

    fun addOnTop(todo: Todo) {
        this.dataSet.add(0,todo)
        notifyItemInserted(0)
    }

    private fun handleClick(viewHolder: ViewHolder){
        viewHolder.onClick { p ->
            dataSet[p].isChecked = !dataSet[p].isChecked
            notifyItemChanged(p)
        }
        viewHolder.onLongClick {

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.update(dataSet[position])
        handleClick(viewHolder)
    }

    override fun getItemCount() = dataSet.size
}