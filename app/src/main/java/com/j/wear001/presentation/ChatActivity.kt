package com.j.wear001.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.j.wear001.R
import com.j.wear001.databinding.ActivityChatBinding
import com.j.wear001.presentation.user.Item

class ChatActivity : AppCompatActivity() {

    var TAG = "log------"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter

    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)

        setContentView(binding.root)



        recyclerView = binding.recyclerView
        adapter = MyAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = mutableListOf<Item>()
        var s = Item("001","xxxxx");
        var s2 = Item("002","222222");
        var s3 = Item("003","3333");
        items.add(s)
        items.add(s2)
        items.add(s3)
        adapter.updateData(items)


    }


    @Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
    @Composable
    fun DefaultPreview() {

    }




}