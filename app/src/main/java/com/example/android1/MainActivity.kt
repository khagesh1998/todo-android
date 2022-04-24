package com.example.android1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.todoInput)
        val button = findViewById<Button>(R.id.todoButton)
        button.setOnClickListener {
            val dummy = input.text.toString()
            Toast.makeText(this,dummy,Toast.LENGTH_LONG).show()
        }
    }
}