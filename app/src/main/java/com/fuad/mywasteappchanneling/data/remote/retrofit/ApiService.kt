package com.fuad.mywasteappchanneling.data.remote.retrofit

import com.fuad.mywasteappchanneling.data.remote.response.ResponseLogin
import com.fuad.mywasteappchanneling.data.remote.response.ResponseRegister
import com.fuad.mywasteappchanneling.data.remote.response.ResponseRiwayat
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("auth/api/v1/register")
    suspend fun register(
        @Field("nama_user") name: String,
        @Field("email_user") email: String,
        @Field("pw_user") password: String,
        @Field("address_user") alamat: String
    ): ResponseRegister

    @FormUrlEncoded
    @POST("auth/api/v1/login")
    suspend fun login(
        @Field("email_user") email: String,
        @Field("pw_user") password: String
    ): ResponseLogin

    @FormUrlEncoded
    @POST("addTransaction")
    suspend fun addTransaction(
        @Field("total_harga") harga: Int,
        @Field("berat_sampah") berat: Int,
        @Field("id_sampah") idSampah: Int,
        @Field("id_jasa") idJasa: Int,
        @Field("id_user") idUser: Int
    ): ResponseRegister

    @GET("showHistory")
    suspend fun getTransaction(
    ): ResponseRiwayat
}