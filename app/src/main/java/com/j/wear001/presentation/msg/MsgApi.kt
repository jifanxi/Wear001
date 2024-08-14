package com.j.wear001.presentation.msg


import com.j.wear001.presentation.user.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MsgApi {
    @GET("servers")
    fun getServerList(@Query("username") username: String, @Query("msg") msg: String): Call<List<Item>>
}