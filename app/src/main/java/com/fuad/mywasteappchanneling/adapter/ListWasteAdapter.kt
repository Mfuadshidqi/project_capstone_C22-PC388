package com.fuad.mywasteappchanneling.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fuad.mywasteappchanneling.databinding.KategoriSampahBinding

class ListWasteAdapter (private val JenisSampah : ArrayList<JenisSampah>) : RecyclerView.Adapter<ListWasteAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ListViewHolder(var binding: KategoriSampahBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = KategoriSampahBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val jenis = JenisSampah[position]
        Glide.with(holder.itemView.context)
            .load(jenis.photo)
            .into(holder.binding.imgSampah)
            holder.binding.tvJenis.text= jenis.name
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(JenisSampah[holder.adapterPosition]) }
    }

    override fun getItemCount() : Int = JenisSampah.size

    interface OnItemClickCallback {
        fun onItemClicked(data: JenisSampah)
    }
}