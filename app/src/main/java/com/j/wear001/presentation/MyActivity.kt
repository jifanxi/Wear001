package com.j.wear001.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.j.wear001.databinding.ActivityMainBinding
import com.j.wear001.databinding.ActivityMyBinding

class MyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Get the data passed from MainActivity
        val someData = intent.getStringExtra("some_data")
    }
}