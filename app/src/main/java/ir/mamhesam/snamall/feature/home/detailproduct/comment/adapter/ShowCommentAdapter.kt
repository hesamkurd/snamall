package ir.mamhesam.snamall.feature.home.detailproduct.comment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.ResponseShowComment

class ShowCommentAdapter(val showComment: List<ResponseShowComment>): RecyclerView.Adapter<ShowCommentAdapter.ShowCommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowCommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_show_comment,parent,false)
        return ShowCommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowCommentViewHolder, position: Int) {

        val commentItem = showComment[position]
        holder.txtTitle.text = commentItem.title
        holder.txtScore.text = commentItem.value
        holder.txtDate.text = commentItem.date
        holder.txtUser.text = commentItem.nameFamily
        holder.txtAdvice.text = commentItem.advice
        holder.txtContent.text = commentItem.content
        val positive:String = commentItem.positive.replace(",","\n")
        holder.txtPosetive.text = positive
        holder.txtNegative.text = commentItem.negative

    }

    override fun getItemCount(): Int = showComment.size

    class ShowCommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtTitle = itemView.findViewById<TextView>(R.id.txt_title)
        val txtScore = itemView.findViewById<TextView>(R.id.txt_score)
        val txtDate = itemView.findViewById<TextView>(R.id.txt_date)
        val txtUser = itemView.findViewById<TextView>(R.id.txt_user)
        val txtAdvice = itemView.findViewById<TextView>(R.id.txt_advice)
        val txtContent = itemView.findViewById<TextView>(R.id.txt_content)
        val txtPosetive = itemView.findViewById<TextView>(R.id.txt_posotive)
        val txtNegative = itemView.findViewById<TextView>(R.id.txt_negative)

    }
}