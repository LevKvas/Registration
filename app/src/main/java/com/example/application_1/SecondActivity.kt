package com.example.application_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity) // to chain with xml file

        val backButton: Button = findViewById(R.id.button)
        backButton.setOnClickListener {
            finish()
        }
    }
}
