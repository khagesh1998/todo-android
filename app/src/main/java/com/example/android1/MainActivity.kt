package com.example.android1

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private var dataSet:MutableList<Todo> = mutableListOf()
    var mAdapter: TodoItem?=null
    var recyclerView: RecyclerView?=null

    private fun hideSoftKeyboard(view: View) {
        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun addTodo(view: View, input: EditText){
        val dummy = input.text.toString().trim()
        if(dummy==""){
            return
        }
        mAdapter?.addOnTop(Todo(dummy))
        findViewById<EditText>(R.id.todoInput).setText("")
//        recyclerView?.scrollToPosition(dataSet.size-1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView?.layoutManager = linearLayoutManager
        mAdapter = TodoItem(dataSet)
        recyclerView?.adapter = mAdapter

        val input = findViewById<EditText>(R.id.todoInput)
        val button = findViewById<Button>(R.id.todoButton)

        // Click on button to add to list
        button.setOnClickListener {view->
            addTodo(view,input)
        }

        // Enter to add to list
        input.setOnKeyListener { view, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                addTodo(view,input)
            }
            false
        }
    }
}