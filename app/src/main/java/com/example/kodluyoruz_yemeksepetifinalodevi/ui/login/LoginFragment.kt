package com.example.kodluyoruz_yemeksepetifinalodevi.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.kodluyoruz_yemeksepetifinalodevi.R
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.LoginFragmentBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var _binding: LoginFragmentBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.btnLogin.setOnClickListener {
            val email = _binding.editTextEmail.editText?.text.toString()
            val password = _binding.editTextPassword.editText?.text.toString()

            viewModel.login(email, password)
                .observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Resource.Status.LOADING -> {

                        }
                        Resource.Status.SUCCESS -> {
                            val action =
                                LoginFragmentDirections.actionLoginFragmentToRestaurantListFragment()
                            findNavController().navigate(action)

                        }
                        Resource.Status.ERROR -> {

                        }
                    }
                })
        }

        _binding.btnRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }
}