package com.example.projectstem.model.group

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.projectstem.R
import com.example.projectstem.model.Group
import java.io.Serializable
import kotlin.properties.Delegates

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
            val idAndLanguage = IdAndLanguage()
            idAndLanguage.setId(holder.itemView.findViewById<TextView>(R.id.tvGroupId).text.toString())
            idAndLanguage.setLanguageFirst(holder.itemView.findViewById<TextView>(R.id.tvPrimaryLanguage).text.toString())
            idAndLanguage.setLanguageSecond(holder.itemView.findViewById<TextView>(R.id.tvSecondaryLanguage).text.toString())
            val b = Bundle()
            b.putSerializable("idAndLanguage", idAndLanguage)
            Navigation.findNavController(view).navigate(R.id.libraryCategoryFragment, b)
        })
    }
    fun setData(group: List<Group>){
        this.groupList = group
        notifyDataSetChanged()
    }

    class IdAndLanguage : Serializable
    {
        private lateinit var id: String
        private lateinit var languageFirst: String
        private lateinit var languageSecond: String

        fun getId(): String? {
            return id
        }

        fun setId(id: String) {
            this.id = id
        }

        fun getLanguageFirst(): String? {
            return languageFirst
        }

        fun setLanguageFirst(language: String?) {
            this.languageFirst = language!!
        }

        fun getLanguageSecond(): String? {
            return languageSecond
        }

        fun setLanguageSecond(language: String?) {
            this.languageSecond = language!!
        }
    }
}