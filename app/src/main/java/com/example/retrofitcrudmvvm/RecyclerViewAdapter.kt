package com.example.retrofitcrudmvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row_list.view.*

class RecyclerViewAdapter(val clickListener:OnItemClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

        var userList = mutableListOf<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_list ,parent ,  false)
        return MyViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener(){
            clickListener.onItemClick(userList[position])
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val textViewName = itemView.textViewName
        val textViewEmail = itemView.textViewEmail
        val textViewStatus = itemView.textViewStatus

            fun bind(data : User){
                textViewName.text = data.name
                textViewEmail.text = data.email
                textViewStatus.text = data.status
            }
    }
    interface OnItemClickListener{
        fun onItemClick(user: User)
    }
}