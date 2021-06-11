package com.example.projectstem.model.testdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectstem.R
import com.example.projectstem.model.Group

class TestListAdapter: RecyclerView.Adapter<TestListAdapter.MyViewHolder>() {

    private var groupList = emptyList<Group>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_test, parent, false))
    }

    override fun getItemCount(): Int {
        return groupList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = groupList[position]
        holder.itemView.findViewById<TextView>(R.id.lId).text = currentItem.group_id.toString()
        holder.itemView.findViewById<TextView>(R.id.testL1).text  = currentItem.language1
        holder.itemView.findViewById<TextView>(R.id.testL2).text  = currentItem.language2
    }

    fun setData(group: List<Group>){
        this.groupList = group
        notifyDataSetChanged()
    }
}