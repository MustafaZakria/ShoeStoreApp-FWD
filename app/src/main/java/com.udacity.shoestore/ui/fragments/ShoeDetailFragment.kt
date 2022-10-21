package com.udacity.shoestore.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.other.Constants.PICK_IMAGE
import com.udacity.shoestore.viewmodels.ShoeViewModel

class ShoeDetailFragment : Fragment() {

    lateinit var binding: FragmentShoeDetailBinding

    val viewModel by activityViewModels<ShoeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)


        setSizeDropMenu()

        setClickListeners()

        return binding.root
    }

    private fun setSizeDropMenu() {
        //size drop down menu
        val sizes = resources.getStringArray(R.array.sizes)
        val arrayAdapterSize = ArrayAdapter(requireContext(), R.layout.dropdown_item, sizes)
        binding.autoTvSize.setAdapter(arrayAdapterSize)
    }

    private fun setClickListeners() {
        binding.btnCancel.setOnClickListener(
            Navigation.createNavigateOnClickListener(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        )

        binding.btnSave.setOnClickListener { view ->
            addShoe()
            view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        binding.ivShoe.setOnClickListener {
            loadPhotoFromGallery()
        }
    }

    private fun loadPhotoFromGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, PICK_IMAGE)
    }

    lateinit var imageUri: Uri

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data?.data!!
            binding.ivShoe.setImageURI(imageUri)
        }
    }

    private fun addShoe() {
        binding.apply {
            val image = imageUri
            val name = etName.text.toString()
            val company = etCompany.text.toString()
            val desc = etDesc.text.toString()
            val size = autoTvSize.text.toString().toDouble()

            val shoe = Shoe(name, size, company, desc, image)
            viewModel.addShoe(shoe)
        }
    }


}