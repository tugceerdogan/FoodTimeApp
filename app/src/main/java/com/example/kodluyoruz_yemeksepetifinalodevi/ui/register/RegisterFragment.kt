package com.example.kodluyoruz_yemeksepetifinalodevi.ui.register

import android.app.AlertDialog
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
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.RegisterFragmentBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var _binding: RegisterFragmentBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.btnRegister.setOnClickListener {
            val name = _binding.editTextName.editText?.text.toString()
            val email = _binding.editTextEmail.editText?.text.toString()
            val password = _binding.editTextPassword.editText?.text.toString()

            _binding.editTextName.visibility = View.GONE
            _binding.editTextEmail.visibility = View.GONE
            _binding.editTextPassword.visibility = View.GONE
            _binding.btnRegister.visibility = View.GONE


            viewModel.register(name, email, password)
                .observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Resource.Status.LOADING -> {

                        }
                        Resource.Status.SUCCESS -> {


                            val action =
                                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                            findNavController().navigate(action)

                        }


                        Resource.Status.ERROR -> {

                            val dialog = AlertDialog.Builder(context)
                                .setTitle("Error")
                                .setMessage("${it.message}")
                                .setPositiveButton("Try Again!") { dialog, button ->
                                    dialog.dismiss()
                                }
                            dialog.show()
                        }


                    }
                })

        }
    }

}