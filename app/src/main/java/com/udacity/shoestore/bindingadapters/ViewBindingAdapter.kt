package com.udacity.shoestore.bindingadapters

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter

class ViewBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUri")
        fun ImageView.loadImage(uri: Uri) {
            this.setImageURI(uri)
        }
    }
}