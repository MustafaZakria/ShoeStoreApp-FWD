package com.udacity.shoestore.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Shoe(var name: String = "", var size: Double = 35.0, var company: String = "", var description: String = "",
                var image: Uri? = null)