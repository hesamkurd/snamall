package ir.zhiran2021.snamall.feature.home.detailproduct.comment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseRatingComment

class ShowRatingCommentAdapter(val ratingComment: List<ResponseRatingComment>): RecyclerView.Adapter<ShowRatingCommentAdapter.RatingCommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingCommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rating,parent,false)
        return RatingCommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: RatingCommentViewHolder, position: Int) {

        val ratingItem = ratingComment[position]
        holder.txtTitle.text = ratingItem.scoreName
        holder.txtScore.text = ratingItem.scoreValue
        holder.prRating.progress = ratingItem.scoreValue.toFloat()

    }

    override fun getItemCount(): Int = ratingComment.size

    class RatingCommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtTitle = itemView.findViewById<TextView>(R.id.txt_ratingItem_title)
        val txtScore = itemView.findViewById<TextView>(R.id.txt_ratingItem_score)
        val prRating = itemView.findViewById<RoundCornerProgressBar>(R.id.roundCornerProgressBar)

    }
}