package com.example.projectstem.model.group

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.projectstem.R
import com.example.projectstem.model.Group
import kotlinx.android.synthetic.main.libray_group_item.view.*

class GroupListAdapter: RecyclerView.Adapter<GroupListAdapter.MyViewHolder>() {

    private var groupList = emptyList<Group>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.libray_group_item, parent, false))
    }

    override fun getItemCount(): Int {
        return groupList.size
    }

    @SuppressLint("CutPasteId")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = groupList[position]
        holder.itemView.findViewById<TextView>(R.id.tvGroupId).text = currentItem.group_id.toString()
        holder.itemView.findViewById<TextView>(R.id.tvPrimaryLanguage).text  = currentItem.language1
        holder.itemView.findViewById<TextView>(R.id.tvSecondaryLanguage).text  = currentItem.language2

        holder.itemView.setOnClickListener(View.OnClickListener {
            view ->
            Toast.makeText(view.context,holder.itemView.findViewById<TextView>(R.id.tvGroupId).text.toString(), Toast.LENGTH_SHORT).show()
            Toast.makeText(view.context,holder.itemView.findViewById<TextView>(R.id.tvPrimaryLanguage).text.toString(), Toast.LENGTH_SHORT).show()
            Toast.makeText(view.context,holder.itemView.findViewById<TextView>(R.id.tvSecondaryLanguage).text.toString(), Toast.LENGTH_SHORT).show()

            l1 = holder.itemView.findViewById<TextView>(R.id.tvGroupId).text.toString()
            Navigation.findNavController(view).navigate(R.id.libraryCategoryFragment)
        })

    }
    fun setData(group: List<Group>){
        this.groupList = group
        notifyDataSetChanged()
    }

    fun getPrimaryLanguage(fLirst: String): String {
        l1 = fLirst
        return l1
    }
    companion object {
        fun getPrimaryLanguage(fLirst: String): String {
            l1 = fLirst
            return l1
        }
    }
}