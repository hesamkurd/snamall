package ir.mamhesam.snamall.feature.home.detailproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.PropertiesItem

class PropertiesAdapter(val properties: List<PropertiesItem>): RecyclerView.Adapter<PropertiesAdapter.PropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_properties,parent,false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val propertiesItem = properties[position]
        holder.txt_title.text = propertiesItem.propertyName
        holder.txt_value.text = propertiesItem.value
    }

    override fun getItemCount(): Int = properties.size

    class PropertyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_title = itemView.findViewById<TextView>(R.id.txt_propertiesChild_title)
        val txt_value = itemView.findViewById<TextView>(R.id.txt_propertiesChild_value)

    }
}