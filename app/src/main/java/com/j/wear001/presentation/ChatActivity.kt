package com.j.wear001.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.j.wear001.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    var TAG = "log------"

    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Get the data passed from MainActivity

        val textbox1 = binding.msg

        binding.button.setOnClickListener {
            // Perform an action when the button is clicked
            Log.e(TAG, "chat....Text box 2 value: ${textbox1.text}")
        }
    }
}