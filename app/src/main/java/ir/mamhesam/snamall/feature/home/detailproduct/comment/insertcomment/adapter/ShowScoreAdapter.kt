package ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.ResponseShowScore
import ir.mamhesam.snamall.data.ScoreItem

class ShowScoreAdapter(val showScore: List<ScoreItem>): RecyclerView.Adapter<ShowScoreAdapter.ShowScoreViewHolder>() {
    lateinit var onChangeSliderScore: OnChangeSliderScore

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_show_score,parent,false)
        return ShowScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowScoreViewHolder, position: Int) {

        val scoreItem = showScore[position]

        holder.txtScore.text = scoreItem.scoreName
        holder.slider.value = scoreItem.value.toFloat()
        holder.slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener{
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: Slider) {

            }

            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: Slider) {
                onChangeSliderScore.onChangeItemScore(slider.value,scoreItem)
            }

        })

    }
    fun setOnSliderChange(onChangeSliderScore: OnChangeSliderScore){
        this.onChangeSliderScore = onChangeSliderScore
    }

    override fun getItemCount(): Int = showScore.size

    class ShowScoreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtScore = itemView.findViewById<TextView>(R.id.txt_score)
        val slider = itemView.findViewById<Slider>(R.id.slider_score)

    }
    interface OnChangeSliderScore{
        fun onChangeItemScore(value:Float,item:ScoreItem)
    }
}