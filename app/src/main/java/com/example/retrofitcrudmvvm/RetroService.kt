package com.example.retrofitcrudmvvm

import retrofit2.Call
import retrofit2.http.*

interface RetroService {

    //https://gorest.co.in/public/v1/users
    @GET("v1/users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUserList(): Call<UserList>

    //https://gorest.co.in/public/v1/users?name=a
    @GET("v1/users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun searchUsers(@Query("name") searchText:String): Call<UserList>

    //https://gorest.co.in/public/v1/users/121
    @GET("v1/users{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUser(@Path("user_id") user_id:String): Call<UserResponse>

    @POST("v1/users")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer f0441ff125cccb5214600ae770e70407e14b63d33c71e4a93f8b5b60dd8f4832")
    fun createUser(@Body params: User):Call<UserResponse>

    @PATCH("v1/users{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer f0441ff125cccb5214600ae770e70407e14b63d33c71e4a93f8b5b60dd8f4832")
    fun updateUser(@Path("user_id") user_id:String , @Body params: User): Call<UserResponse>

    @DELETE("v1/users{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer f0441ff125cccb5214600ae770e70407e14b63d33c71e4a93f8b5b60dd8f4832")
    fun deleteUser(@Path("user_id") user_id:String): Call<UserResponse>
}