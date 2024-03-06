package com.example.junjin.ui.home.adapter

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.junjin.R
import com.example.junjin.model.network.api.AccountApi
import com.example.junjin.model.network.dto.SubComment

class SubCommentAdapter : RecyclerView.Adapter<SubCommentAdapter.SubCommentViewHolder>() {
    // 嵌套 RecyclerView 的数据源
    private var subComments: List<SubComment> = emptyList()

    // 提交新的数据列表
    fun submitList(newList: List<SubComment>) {
        subComments = newList
        notifyDataSetChanged()
    }

    // 创建 ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCommentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_sub_comment, parent, false)
        return SubCommentViewHolder(itemView)
    }

    // 绑定 ViewHolder
    override fun onBindViewHolder(holder: SubCommentViewHolder, position: Int) {
        val currentSubComment = subComments[position]
        if (subComments.isNotEmpty()) {
            holder.bind(currentSubComment)
            holder.itemView.visibility = View.VISIBLE
        } else {
            // 如果 SubComment 为空，则隐藏布局
            holder.itemView.visibility = View.GONE
        }
    }

    // 返回数据源的大小
    override fun getItemCount() = subComments.size

    // 定义 SubCommentViewHolder 类
    inner class SubCommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView: ImageView = itemView.findViewById(R.id.the_avatar2)
        val nameTextView: TextView = itemView.findViewById(R.id.the_name2)
        val subCommentTextView: TextView = itemView.findViewById(R.id.sub_comment)

        fun bind(subComment: SubComment) {
            nameTextView.text = subComment.username
            subCommentTextView.text = subComment.context

            val base64String = subComment.avatar
            val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            avatarImageView.setImageBitmap(bitmap)
        }
    }
}