package ir.zhiran2021.snamall.feature.profile.address.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.data.ResponseShowAddress

class ShowAddressAdapter(val listAddress: List<ResponseShowAddress>) :
    RecyclerView.Adapter<ShowAddressAdapter.AddressViewHolder>() {
    var onCLickAdderss: OnClickItemAddress? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_adress, parent, false)
        return AddressViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {

        val itemAddress = listAddress[position]
        holder.txtAddress.text = itemAddress.address
        holder.txtNameFamily.text = itemAddress.nameFamily
        holder.txtPhone.text = itemAddress.phone
        holder.txtPostalCode.text = itemAddress.postalCode
        holder.itemView.setOnClickListener {

            onCLickAdderss!!.onClickAddress(itemAddress)
        }

    }

    override fun getItemCount(): Int = listAddress.size
    fun setOnCLickAddress(onCLickAdder: OnClickItemAddress) {
        this.onCLickAdderss = onCLickAdder
    }

    class AddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtAddress = itemView.findViewById<TextView>(R.id.txt_adress)
        val txtNameFamily = itemView.findViewById<TextView>(R.id.txt_family)
        val txtPhone = itemView.findViewById<TextView>(R.id.txt_phone)
        val txtPostalCode = itemView.findViewById<TextView>(R.id.txt_postalcode)


    }

    interface OnClickItemAddress {
        fun onClickAddress(address: ResponseShowAddress)
    }
}