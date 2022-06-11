package com.fuad.mywasteappchanneling.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Waste(
    var name: String,
    var photo: String
) : Parcelable