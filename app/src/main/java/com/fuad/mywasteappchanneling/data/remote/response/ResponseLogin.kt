package com.fuad.mywasteappchanneling.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("currUser")
	val currUser: Int,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
