package com.fuad.mywasteappchanneling.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseRiwayat(

	@field:SerializedName("values")
	val values: ArrayList<ValuesItem>,

	@field:SerializedName("status")
	val status: Int? = null
)

data class ValuesItem(

	@field:SerializedName("nama_sampah")
	val namaSampah: String? = null,

	@field:SerializedName("total_harga")
	val totalHarga: Int? = null,

	@field:SerializedName("berat_sampah")
	val beratSampah: Int? = null
)
