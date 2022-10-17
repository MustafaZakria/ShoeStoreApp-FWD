package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
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

    private fun setClickListeners() {
        binding.btnCancel.setOnClickListener(
            Navigation.createNavigateOnClickListener(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        )

        binding.btnSave.setOnClickListener(
            Navigation.createNavigateOnClickListener(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        )
    }

    private fun setSizeDropMenu() {
        //size drop down menu
        val sizes = resources.getStringArray(R.array.sizes)
        val arrayAdapterSize = ArrayAdapter(requireContext(), R.layout.dropdown_item, sizes)
        binding.autoTvSize.setAdapter(arrayAdapterSize)
    }

}