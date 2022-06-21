package ir.zhiran2021.snamall.feature.profile.auoth.rules.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponsePrivacy
import ir.zhiran2021.snamall.data.ResponseRules

class RulesAdapter(val items:List<ResponseRules>):RecyclerView.Adapter<RulesAdapter.PrivacyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrivacyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rules,parent,false)
        return PrivacyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrivacyViewHolder, position: Int) {
        val itemPrivacy = items[position]
        holder.txtPrivacy.text = itemPrivacy.text
    }

    override fun getItemCount(): Int = items.size

    class PrivacyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val txtPrivacy = itemView.findViewById<TextView>(R.id.txt_rules)
    }
}