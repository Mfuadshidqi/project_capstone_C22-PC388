package com.fuad.mywasteappchanneling.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JenisSampah(
    var name: String,
    var photo: Int
) : Parcelable
