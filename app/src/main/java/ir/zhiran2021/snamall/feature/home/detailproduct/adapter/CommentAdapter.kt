package ir.zhiran2021.snamall.feature.home.detailproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.CommentsItem

class CommentAdapter(val comments: List<CommentsItem>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment,parent,false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val commentItem = comments[position]
        holder.txt_title.text = commentItem.title
        holder.txt_content.text = commentItem.content
        holder.txt_date.text = commentItem.commentData
        holder.txt_user.text = commentItem.nameFamily
    }

    override fun getItemCount(): Int = comments.size

    class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_title = itemView.findViewById<TextView>(R.id.txt_title_comment)
        val txt_content = itemView.findViewById<TextView>(R.id.txt_content_comment)
        val txt_date = itemView.findViewById<TextView>(R.id.txt_date_comment)
        val txt_user = itemView.findViewById<TextView>(R.id.txt_user_comment)

    }
}