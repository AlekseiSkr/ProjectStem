package com.example.projectstem.model.testdb

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.projectstem.R
import com.example.projectstem.model.Word
import java.io.Serializable

class WordListAdapter: RecyclerView.Adapter<WordListAdapter.MyViewHolder>() {
    private var wordList = emptyList<Word>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){ }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.library_word_item, parent, false))
    }

    override fun getItemCount(): Int {

        return wordList.size
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = wordList[position]
        val wordAndLanguage = WordAndLanguage()
        val b = Bundle()
        b.putSerializable("wordAndLanguage", wordAndLanguage)
        // Word itself
        holder.itemView.findViewById<TextView>(R.id.origin).text = currentItem.original
        holder.itemView.findViewById<TextView>(R.id.translation).text = currentItem.translation
        holder.itemView.findViewById<TextView>(R.id.knowledge).text = currentItem.knowledge.toString()
        holder.itemView.findViewById<TextView>(R.id.groupLanguage).text = currentItem.group_language_id.toString()
        holder.itemView.findViewById<TextView>(R.id.wordId).text = currentItem.word_id.toString()

        holder.itemView.setOnClickListener ( View.OnClickListener {
            view ->
            wordAndLanguage.setWord(holder.itemView.findViewById<TextView>(R.id.wordId).text.toString())
            wordAndLanguage.setOriginal(holder.itemView.findViewById<TextView>(R.id.origin).text.toString())
            wordAndLanguage.setTranslation(holder.itemView.findViewById<TextView>(R.id.translation).text.toString())
            wordAndLanguage.setLanguageGroupId(holder.itemView.findViewById<TextView>(R.id.groupLanguage).text.toString())
            Navigation.findNavController(view).navigate(R.id.library_word, b)
        } )

    }
    fun setData(word: List<Word>){
        this.wordList = word
        notifyDataSetChanged()
    }

    class WordAndLanguage: Serializable {
        private lateinit var word: String
        private lateinit var original: String
        private lateinit var translation: String
        private lateinit var languageFirst: String
        private lateinit var languageSecond: String

        fun getWord(): String? {
            return word
        }

        fun setWord(word: String) {
            this.word = word
        }

        fun getOriginal(): String? {
            return original
        }

        fun setOriginal(original: String) {
            this.original = original
        }

        fun getTranslation(): String? {
            return translation
        }

        fun setTranslation(translation: String) {
            this.translation = translation
        }
        fun getLanguageGroupId(): String? {
            return languageFirst
        }

        fun setLanguageGroupId(language: String?) {
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
