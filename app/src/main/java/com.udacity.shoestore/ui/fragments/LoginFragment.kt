package com.udacity.shoestore.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.other.Constants.KEY_FIRST_TIME_OPEN
import com.udacity.shoestore.other.Constants.KEY_LOGGED_IN_STATE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var sharedPref: SharedPreferences

    @set:Inject
    var isFirstAppOpen = true

    var isLoggedIn = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.btnLogin.setOnClickListener {
            buttonClickedNavigation(it)
        }

        binding.btnRegister.setOnClickListener {
            buttonClickedNavigation(it)
        }

        isLoggedIn = sharedPref.getBoolean(KEY_LOGGED_IN_STATE, false)

        if (!isFirstAppOpen && isLoggedIn) {
            this.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToShoeListFragment2())
        }

        return binding.root
    }


    private fun writeToSharedPref() {
        sharedPref.edit()
            .putBoolean(KEY_FIRST_TIME_OPEN, false)
            .apply()

        sharedPref.edit()
            .putBoolean(KEY_LOGGED_IN_STATE, true)
            .apply()
    }

    private fun buttonClickedNavigation(view: View) {

        isFirstAppOpen = sharedPref.getBoolean(KEY_FIRST_TIME_OPEN, true)

        writeToSharedPref()

        if (isFirstAppOpen) {
            view.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        } else {
            view.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToShoeListFragment2())
        }

    }

}