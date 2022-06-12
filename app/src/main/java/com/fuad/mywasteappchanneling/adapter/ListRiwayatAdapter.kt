package com.fuad.mywasteappchanneling.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fuad.mywasteappchanneling.data.remote.response.ValuesItem
import com.fuad.mywasteappchanneling.databinding.CardRiwayatBinding
import java.util.*
import kotlin.collections.ArrayList

class ListRiwayatAdapter : RecyclerView.Adapter<ListRiwayatAdapter.ListViewHolder>() {

    private var listData = ArrayList<ValuesItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: ArrayList<ValuesItem>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    class ListViewHolder(var binding: CardRiwayatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            CardRiwayatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val riwayat = listData[position]
        holder.binding.tvJenis.text = riwayat.namaSampah?.uppercase(Locale.getDefault())
        holder.binding.tvBeratRiwayat.text = riwayat.beratSampah.toString()
        holder.binding.tvHargaRiwayat.text = riwayat.totalHarga.toString()
    }

    override fun getItemCount(): Int = listData.size

}