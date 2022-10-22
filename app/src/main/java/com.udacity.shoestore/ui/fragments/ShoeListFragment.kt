package com.udacity.shoestore.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.other.Constants
import com.udacity.shoestore.viewmodels.ShoeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShoeListFragment : Fragment() {

    @Inject
    lateinit var sharedPref: SharedPreferences

    lateinit var binding: FragmentShoeListBinding

    val viewModel by activityViewModels<ShoeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        setHasOptionsMenu(true)

        binding.fab.setOnClickListener(
            Navigation.createNavigateOnClickListener(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        )

        viewModel.listOfShoes.observe(viewLifecycleOwner, Observer { listOfShoes ->
            if (listOfShoes != null && listOfShoes.isNotEmpty()) {
                displayShoes(listOfShoes)
            }
        })

        return binding.root
    }

    private fun displayShoes(listOfShoes: MutableList<Shoe>) {
        for (shoe in listOfShoes) {
            val itemBinding = ItemShoeBinding.inflate(layoutInflater)
            itemBinding.shoe = shoe

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 5)

            binding.layoutRoot.addView(itemBinding.cardView, layoutParams)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        writeToSharedPref()
        this.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        return super.onOptionsItemSelected(item)

    }

    private fun writeToSharedPref() {
        sharedPref.edit()
            .putBoolean(Constants.KEY_LOGGED_IN_STATE, false)
            .apply()
    }

}