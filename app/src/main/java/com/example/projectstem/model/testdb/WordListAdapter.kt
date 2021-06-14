package com.example.projectstem.model.testdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectstem.R
import com.example.projectstem.model.Group
import com.example.projectstem.model.Word
import com.example.projectstem.model.group.GroupListAdapter
import com.example.projectstem.model.group.GroupViewModel

class WordListAdapter: RecyclerView.Adapter<WordListAdapter.MyViewHolder>() {
    private var wordList = emptyList<Word>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){ }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.library_word_item, parent, false))
    }

    override fun getItemCount(): Int {

        return wordList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = wordList[position]
        holder.itemView.findViewById<TextView>(R.id.origin).text = currentItem.original
    }

    fun setData(word: List<Word>){
        this.wordList = word
        notifyDataSetChanged()
    }
}
