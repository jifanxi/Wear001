package com.j.wear001.presentation.msg


import com.j.wear001.presentation.user.Item
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MsgApi {
    @GET("/messages")
    fun getMessageList(@Query("username") username: String, @Query("msg") msg: String): Call<List<Item>>

    @POST("/addMsg")
    fun addMsg( @Query("username") username: String,
                @Query("msg") msg: String): Call<List<Item>>
}