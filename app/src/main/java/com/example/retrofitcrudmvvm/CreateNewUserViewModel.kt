package com.example.retrofitcrudmvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateNewUserViewModel:ViewModel() {

    private lateinit var createNewUserLiveData: MutableLiveData<UserResponse?>
    private lateinit var loadUserData: MutableLiveData<UserResponse?>
    private lateinit var deleteUserData: MutableLiveData<UserResponse?>

    init{
        createNewUserLiveData = MutableLiveData()
        loadUserData = MutableLiveData()
        deleteUserData = MutableLiveData()
    }

    fun getCreateNewUserObseravable():MutableLiveData<UserResponse?>{
        return createNewUserLiveData
    }

    fun getloadUserObseravable():MutableLiveData<UserResponse?>{
        return loadUserData
    }
    fun getdeleteUserObseravable():MutableLiveData<UserResponse?>{
        return deleteUserData
    }
    fun createUser(user:User){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.createUser(user)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                }
                else{
                    createNewUserLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }
        })
    }

    fun updateUser(user_id: String, user:User){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.updateUser(user_id , user)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                }
                else{
                    createNewUserLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }
        })
    }

    fun deleteUser(user_id: String?){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.deleteUser(user_id!!)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful) {
                    deleteUserData.postValue(response.body())
                }
                else{
                    deleteUserData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                deleteUserData.postValue(null)
            }
        })
    }

    fun getUserData(user_id:String?){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getUser(user_id!!)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful) {
                    loadUserData.postValue(response.body())
                }
                else{
                    loadUserData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                loadUserData.postValue(null)
            }
        })
    }
}