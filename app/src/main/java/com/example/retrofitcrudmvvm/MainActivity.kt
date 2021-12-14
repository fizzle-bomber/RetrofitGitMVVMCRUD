package com.example.retrofitcrudmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,RecyclerViewAdapter.OnItemClickListener{
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            initRecyclerView()
            initViewModel()
            searchUser()
        createUserButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, CreateNewUserActivity::class.java))
        }
    }

    private fun searchUser(){
        search_button.setOnClickListener{
            if(!TextUtils.isEmpty(searchEditText.text.toString())){
                viewModel.searchUser(searchEditText.text.toString())
            }
            else{
                Toast.makeText(this , "In else part", Toast.LENGTH_SHORT).show()
                viewModel.getUserList()
            }
        }
    }

    private fun initRecyclerView(){
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity , DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUserListObservable().observe(this, Observer {
            if (it == null) {
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show()
            } else {
                recyclerViewAdapter.userList = it.data.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }

        })
        viewModel.getUserList()
    }

    override fun onItemClick(user: User) {
        val intent = Intent(this , CreateNewUserActivity::class.java)
        intent.putExtra("user_id" , user.id)
        startActivityForResult(intent , 1000)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1000){
            viewModel.getUserList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}