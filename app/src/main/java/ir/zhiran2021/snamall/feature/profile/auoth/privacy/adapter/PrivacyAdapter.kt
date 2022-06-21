package ir.zhiran2021.snamall.feature.profile.auoth.privacy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponsePrivacy

class PrivacyAdapter(val items:List<ResponsePrivacy>):RecyclerView.Adapter<PrivacyAdapter.PrivacyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrivacyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_privacy,parent,false)
        return PrivacyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrivacyViewHolder, position: Int) {
        val itemPrivacy = items[position]
        holder.txtPrivacy.text = itemPrivacy.text
    }

    override fun getItemCount(): Int = items.size

    class PrivacyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val txtPrivacy = itemView.findViewById<TextView>(R.id.txt_privacy)
    }
}