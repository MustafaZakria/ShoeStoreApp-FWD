package com.udacity.shoestore.bindingadapters

import android.net.Uri
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

class ViewBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUri")
        fun ImageView.loadImage(uri: Uri) {
            this.setImageURI(uri)
        }

    }
}