package com.example.kodluyoruz_yemeksepetifinalodevi.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kodluyoruz_yemeksepetifinalodevi.R
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.profile.User
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.UserFragmentBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import com.example.kodluyoruz_yemeksepetifinalodevi.util.gone
import com.example.kodluyoruz_yemeksepetifinalodevi.util.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserFragment : Fragment() {
    private lateinit var _binding: UserFragmentBinding
    private val viewModel: UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    companion object {
        fun getImageResource(image: String?): Int {
            val resource = try {
                image?.toInt()
            } catch (e: Exception) {
                Log.v("Profile Avatar", e.message.toString())
                R.drawable.food_blogger
            }
            return resource ?: R.drawable.food_blogger
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.profileProgressBar.show()
        addListeners()
        getProfile()
    }

    private fun getProfile() {
        viewModel.getUser().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    setLoading(true)
                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)
                    setField(response.data?.user)
                }
                Resource.Status.ERROR -> {
                    setLoading(false)
                    Toast.makeText(context, "Operation Failed", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            _binding.profileProgressBar.show()
            _binding.myProfileTextView.gone()
            _binding.ProfileCardView.gone()
            _binding.linearLayout2.gone()

        } else {
            _binding.profileProgressBar.gone()
            _binding.myProfileTextView.show()
            _binding.ProfileCardView.show()
            _binding.linearLayout2.show()
        }
    }

    private fun setField(user: User?) {
        _binding.nameTextView.text = user?.name
        _binding.mailTextView.text = user?.email
        _binding.phoneNumberTextView.text = user?.phone
        _binding.addressTextView.text = user?.address
        _binding.profilePhotoImageView.setImageResource(getImageResource(user?.profileImage))
    }

    private fun addListeners() {
        _binding.changeProfile.setOnClickListener {
            findNavController().navigate(R.id.action_userFragment_to_settingFragment)

        }
        _binding.logOutCardView.setOnClickListener {
            viewModel.logOut()
            val action = UserFragmentDirections.actionUserFragmentToSplashFragment()
            findNavController().navigate(action)
        }
    }
}