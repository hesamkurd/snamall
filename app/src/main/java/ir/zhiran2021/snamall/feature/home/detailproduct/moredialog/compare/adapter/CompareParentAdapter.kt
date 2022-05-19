package ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.PropertyItemCompare

class CompareParentAdapter(private val compare: List<PropertyItemCompare>): RecyclerView.Adapter<CompareParentAdapter.CompareCategoryViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompareCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_compare_parent,parent,false)
        return CompareCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompareCategoryViewHolder, position: Int) {
        val itemCompares = compare[position]
        holder.txtparent.text = itemCompares.mainCategory

        val linearLayoutManager = LinearLayoutManager(holder.rcparent.context)
        linearLayoutManager.initialPrefetchItemCount = itemCompares.value.size
        val itemDecoration :RecyclerView.ItemDecoration = DividerItemDecoration(holder.rcparent.context,
            DividerItemDecoration.VERTICAL)


        val compareChildAdapter = CompareChildAdapter(itemCompares.value)
        holder.rcparent.layoutManager = linearLayoutManager
        holder.rcparent.adapter = compareChildAdapter
        holder.rcparent.setRecycledViewPool(viewPool)
        holder.rcparent.addItemDecoration(itemDecoration)


    }

    override fun getItemCount(): Int = compare.size

    class CompareCategoryViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        val txtparent = itemView.findViewById<TextView>(R.id.txt_parent)
        val rcparent = itemView.findViewById<RecyclerView>(R.id.rc_pr)
    }

}