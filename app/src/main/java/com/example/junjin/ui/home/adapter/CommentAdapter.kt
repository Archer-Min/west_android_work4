package com.example.junjin.ui.home.adapter

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.junjin.R
import com.example.junjin.model.network.api.AccountApi
import com.example.junjin.model.network.dto.Comment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    private var comments: List<Comment> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Comment>) {
        comments = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currentComment = comments[position]
        holder.bind(currentComment)
    }

    override fun getItemCount() = comments.size

    // 定义CommentViewHolder类
    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView: ImageView = itemView.findViewById(R.id.the_avatar)
        val nameTextView: TextView = itemView.findViewById(R.id.the_name)
        val commentTextView: TextView = itemView.findViewById(R.id.the_comment)
        val subCommentRecyclerView: RecyclerView = itemView.findViewById(R.id.sub_comment_recycler)

        fun bind(comment: Comment){
            nameTextView.text = comment.username
            commentTextView.text = comment.context

            val base64String = comment.avatar
            val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            avatarImageView.setImageBitmap(bitmap)

            if (comment.subComments.isEmpty()){
                subCommentRecyclerView.visibility = View.GONE
            }else{
                //设置子评论的recyclerView适配器
                val subCommentAdapter = SubCommentAdapter()
                subCommentAdapter.submitList(comment.subComments)
                subCommentRecyclerView.adapter = subCommentAdapter
            }
        }
    }
}