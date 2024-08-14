package com.j.wear001.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.j.wear001.R
import com.j.wear001.databinding.ActivityChatBinding
import com.j.wear001.presentation.msg.MsgApi
import com.j.wear001.presentation.user.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatActivity : AppCompatActivity() {

    var TAG = "log------"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private val adapterItems = mutableListOf<Item>()

    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)

        setContentView(binding.root)



        recyclerView = binding.recyclerView
        adapter =  MyAdapter(adapterItems) { item ->
            //item.isSelected =!item.isSelected
            adapter.notifyItemChanged(adapterItems.indexOf(item))
        }
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

        binding.sendButton.setOnClickListener {
            val items = mutableListOf<Item>()
            var s = Item("001","xxxxx");
            var s2 = Item("002","222222");
            items.add(s)
            items.add(s2)
            adapter.updateData(items)

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 0)
            } else {
                // 已经有权限，可以使用网络套接字
            }




            val retrofit = Retrofit.Builder()
                .baseUrl("http://localhost:8085/messages/") // 服务器的基URL
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .build()

            val serverApi = retrofit.create(MsgApi::class.java)
            val call = serverApi.getServerList("aaa","aaa")

            call.enqueue(object : Callback<List<Item>> {
                override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {

                    Log.e(TAG, "Error: Text box 1 value: ${response}");
                    Log.e(TAG, "Error: Text box 1 value: ${response}");
                    if (response.isSuccessful) {
                        val servers = response.body() // 获取服务器列表
                        //adapter.servers = servers?: emptyList()
                        adapter.notifyDataSetChanged()
                    } else {
                        // 处理服务器响应错误的情况
                    }
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    // 处理网络请求失败的情况
                    Log.e(TAG, "Error: Text box 1 value: ${t}");
                    Log.e(TAG, "Error: Text box 1 value: ${t}");

                }
            })
        }

    }


    @Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
    @Composable
    fun DefaultPreview() {

    }




}