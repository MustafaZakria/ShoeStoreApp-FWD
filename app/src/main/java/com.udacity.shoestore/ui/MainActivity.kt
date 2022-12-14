package com.udacity.shoestore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        this.findNavController(R.id.navHostFragment)
            .addOnDestinationChangedListener{ _, dest, _ ->
                when(dest.id) {
                    R.id.loginFragment, R.id.welcomeFragment, R.id.instructionsFragment ->
                        binding.layoutAppBar.visibility = View.GONE
                    else -> binding.layoutAppBar.visibility = View.VISIBLE
                }
            }

    }
}
