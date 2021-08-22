package com.example.kodluyoruz_yemeksepetifinalodevi.ui.setting


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kodluyoruz_yemeksepetifinalodevi.R
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.profile.User
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.profile.UserRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.SettingFragmentBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.ui.profile.UserFragment
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import com.example.kodluyoruz_yemeksepetifinalodevi.util.gone
import com.example.kodluyoruz_yemeksepetifinalodevi.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {
    private lateinit var _binding: SettingFragmentBinding
    private val viewModel: SettingViewModel by viewModels()
    private var image: Int = R.drawable.empty_cart_icon

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SettingFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        addListeners()
    }

    private fun initViews() {
        viewModel.getUser().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    setField(response.data?.user)
                    isSettingVisible(true)
                }
                Resource.Status.ERROR -> {
                    isSettingVisible(false)
                }
            }
        })
    }

    private fun setField(user: User?) {
        _binding.nameEditText.setText(user?.name)
        _binding.mailEditText.setText(user?.email)
        _binding.addressEditText.setText(user?.address)
        _binding.phoneNumberEditText.setText(user?.phone)
        user?.paymentMethod?.let { _binding.paymentRadioGroup.check(it) }

        image = UserFragment.getImageResource(user?.profileImage)
        _binding.avatarImageView.setImageResource(UserFragment.getImageResource(user?.profileImage))
    }

    private fun addListeners() {
        _binding.updateButton.setOnClickListener { updateUser() }
        _binding.backImageView.setOnClickListener { findNavController().popBackStack() }
    }


    private fun updateUser() {
        val name = _binding.nameEditText.text.toString()
        val mail = _binding.mailEditText.text.toString()
        val phone = _binding.phoneNumberEditText.text.toString()
        val address = _binding.addressEditText.text.toString()
        val paymentMethod = _binding.paymentRadioGroup.checkedRadioButtonId

        val user = UserRequest(mail, name, address, phone, image.toString(), paymentMethod)
        viewModel.updateUser(user).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    findNavController().navigate(R.id.action_settingFragment_to_userFragment)
                    isSettingVisible(true)
                }
                Resource.Status.ERROR -> {
                    isSettingVisible(false)
                }
            }
        })
    }

    private fun isSettingVisible(isVisible: Boolean) {
        _binding.progressBar.gone()
        _binding.container.isVisible = isVisible
        if (isVisible.not()) {
            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("There is a problem")
                .setPositiveButton("Cancel") { _, _ ->
                    findNavController().popBackStack()
                }.show()
        }
    }


}

