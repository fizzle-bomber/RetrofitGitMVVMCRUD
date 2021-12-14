package com.example.retrofitcrudmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_create_user.*

class CreateNewUserActivity : AppCompatActivity() {
    lateinit var createNewUserViewModel: CreateNewUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        val user_id = intent.getStringExtra("user_id")
        initViewModel()
        createUserObservable()
        if(user_id != null)
        {
            loadUser(user_id)
        }
        createButton.setOnClickListener {
            createUser(user_id)
        }
        deleteButton.setOnClickListener {
            deleteUser(user_id)
        }
    }

    private fun loadUser(user_id:String?){
        createNewUserViewModel.getloadUserObseravable().observe(this , Observer {
            if(it != null)
            {
                editTextName.setText(it.data?.name)
                editTextEmail.setText(it.data?.email)
                createButton.setText("Update")
                deleteButton.visibility = VISIBLE
            }
            else
            {
                Toast.makeText(this , "Successfully created the new user" , Toast.LENGTH_SHORT).show()
            }
        })
        createNewUserViewModel.getUserData(user_id)
    }

    private fun createUser(user_id: String?){
        val user = User("" , editTextName.text.toString() , editTextEmail.text.toString(),"Active","Male")
        if(user_id==null) {
            createNewUserViewModel.createUser(user)
        }
        else{
            createNewUserViewModel.updateUser(user_id , user)
        }
    }

    private fun deleteUser(user_id: String?){
        createNewUserViewModel.getdeleteUserObseravable().observe(this , Observer {
            if(it == null)
            {
                Toast.makeText(this , "Failed to delete new user" , Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this , "Successfully deleted the new user" , Toast.LENGTH_SHORT).show()
                finish()
            }
    })
        createNewUserViewModel.deleteUser(user_id)
    }
    private fun initViewModel(){
        createNewUserViewModel = ViewModelProvider(this).get(CreateNewUserViewModel::class.java)
    }

    private fun createUserObservable(){
        createNewUserViewModel.getCreateNewUserObseravable().observe(this , Observer {
            if(it == null)
            {
                Toast.makeText(this , "Failed to create new user" , Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this , "Successfully created the new user" , Toast.LENGTH_SHORT).show()
            }
        })
    }
}