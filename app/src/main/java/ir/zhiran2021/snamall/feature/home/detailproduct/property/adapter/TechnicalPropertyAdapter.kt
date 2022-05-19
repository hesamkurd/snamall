package ir.zhiran2021.snamall.feature.home.detailproduct.property.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseTechnicalProperty

class TechnicalPropertyAdapter(val properties: List<ResponseTechnicalProperty>): RecyclerView.Adapter<TechnicalPropertyAdapter.PropertyViewHolder>() {

    val viewPool= RecyclerView.RecycledViewPool()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_property_parent,parent,false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {

        val propertyItem = properties[position]
        holder.txtTitle.text = propertyItem.mainCategory
        val layoutManager = LinearLayoutManager(holder.rcChild.context,LinearLayoutManager.VERTICAL,false)
        layoutManager.initialPrefetchItemCount = propertyItem.property.size
        val technicalPropertyChildAdapter= TechnicalPropertyChildAdapter(propertyItem.property)
        holder.rcChild.layoutManager = layoutManager
        holder.rcChild.adapter = technicalPropertyChildAdapter
        holder.rcChild.setRecycledViewPool(viewPool)
    }

    override fun getItemCount(): Int = properties.size

    class PropertyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtTitle = itemView.findViewById<TextView>(R.id.txt_parent)
        val rcChild = itemView.findViewById<RecyclerView>(R.id.rc_product_property_child)

    }
}