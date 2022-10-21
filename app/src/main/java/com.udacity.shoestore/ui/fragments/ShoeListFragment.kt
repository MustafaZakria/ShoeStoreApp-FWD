package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.viewmodels.ShoeViewModel


class ShoeListFragment : Fragment() {

    lateinit var binding: FragmentShoeListBinding
    lateinit var itemBinding: ItemShoeBinding

    val viewModel by activityViewModels<ShoeViewModel>()

    //lateinit var curShoe: shoe

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)



        setHasOptionsMenu(true)

        binding.fab.setOnClickListener (
            Navigation.createNavigateOnClickListener(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        )

        viewModel.listOfShoes.observe(viewLifecycleOwner, Observer { listOfShoes ->
            if(listOfShoes != null && listOfShoes.isNotEmpty()) {
                for(shoe in listOfShoes) {
                    itemBinding = ItemShoeBinding.inflate(layoutInflater)
                    itemBinding.shoe = shoe

                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams.setMargins(0, 0, 0, 5)

                    binding.layoutRoot.addView(itemBinding.cardView, layoutParams)
                }
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}