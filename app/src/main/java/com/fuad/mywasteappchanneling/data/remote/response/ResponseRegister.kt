package com.fuad.mywasteappchanneling.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseRegister(

	@field:SerializedName("values")
	val values: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
