package ir.mamhesam.snamall.feature.home.detailproduct.property.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.data.PropertyItem
import ir.mamhesam.snamall.data.ResponseTechnicalProperty

class TechnicalPropertyChildAdapter(val properties: List<PropertyItem>): RecyclerView.Adapter<TechnicalPropertyChildAdapter.PropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_property_child,parent,false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {

        val propertyItem = properties[position]
        holder.txtTitle.text = propertyItem.title
        holder.txtValue.text = propertyItem.property
    }

    override fun getItemCount(): Int = properties.size

    class PropertyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtTitle = itemView.findViewById<TextView>(R.id.txt_name_peroduct)
        val txtValue = itemView.findViewById<TextView>(R.id.txt_value_product)

    }
}