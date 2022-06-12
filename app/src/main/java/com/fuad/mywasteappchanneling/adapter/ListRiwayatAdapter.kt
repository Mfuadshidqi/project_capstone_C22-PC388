package com.fuad.mywasteappchanneling.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.SharedValues
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fuad.mywasteappchanneling.data.remote.response.ValuesItem
import com.fuad.mywasteappchanneling.databinding.CardRiwayatBinding
import com.fuad.mywasteappchanneling.databinding.KategoriSampahBinding

class ListRiwayatAdapter : RecyclerView.Adapter<ListRiwayatAdapter.ListViewHolder>() {

//    private lateinit var onItemClickCallback: OnItemClickCallback
    private var listData = ArrayList<ValuesItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: ArrayList<ValuesItem>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }
    class ListViewHolder(var binding: CardRiwayatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = CardRiwayatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val riwayat = listData[position]
        holder.binding.tvJenis.text= riwayat.namaSampah?.toUpperCase()
        holder.binding.tvBeratRiwayat.text = riwayat.beratSampah.toString()
        holder.binding.tvHargaRiwayat.text = riwayat.totalHarga.toString()
    }

    override fun getItemCount() : Int = listData.size

//    interface OnItemClickCallback {
//        fun onItemClicked(data: ValuesItem)
//    }
}