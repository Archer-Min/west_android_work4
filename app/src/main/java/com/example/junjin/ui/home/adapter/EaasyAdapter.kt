package com.example.junjin.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.junjin.R
import com.example.junjin.model.network.dto.Article
import com.example.junjin.ui.home.activity.EssayActivity
import com.example.junjin.ui.home.viewModel.EssayViewModel

class EssayAdapter(private val viewModel: EssayViewModel) : RecyclerView.Adapter<EssayAdapter.EssayViewHolder>() {

    private var essays: List<Article> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Article>) {
        essays = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EssayViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_essay_card, parent, false)
        return EssayViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EssayViewHolder, position: Int) {
        val currentEssay = essays[position]
        holder.bind(currentEssay)
        holder.itemView.setOnClickListener{
            viewModel.incrementClickCount(position)

            val intent = Intent(holder.itemView.context, EssayActivity::class.java)
            intent.putExtra("articleId",position)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = essays.size

    inner class EssayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.essay_id)
        private val titleTextView: TextView = itemView.findViewById(R.id.essay_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.date)
        private val likesTextView: TextView = itemView.findViewById(R.id.likes)
        private val clicksTextView: TextView = itemView.findViewById(R.id.clicks)

        fun bind(essay: Article) {
            idTextView.text = essay.articleId.toString()
            titleTextView.text = essay.title
            dateTextView.text = essay.date
            likesTextView.text = essay.like.toString()
            clicksTextView.text = essay.clickCount.toString()
        }
    }
}