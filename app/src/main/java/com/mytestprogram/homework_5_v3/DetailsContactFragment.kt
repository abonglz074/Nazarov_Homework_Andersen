package com.mytestprogram.homework_5_v3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.mytestprogram.homework_5_v3.databinding.FragmentContactDetailsBinding

class DetailsContactFragment : Fragment() {

    private lateinit var binding: FragmentContactDetailsBinding

    private val user: User
        get() = requireArguments().getSerializable(ARG_USER) as User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        binding.contactName.text = user.name
        binding.contactSurname.text = user.surname
        binding.contactPhoneNumber.text = user.phoneNumber
        return binding.root
    }

    companion object {
        private const val ARG_USER = "ARG_USER"

        fun newInstance(user: User): DetailsContactFragment {
            val fragment = DetailsContactFragment()
            fragment.arguments = bundleOf(ARG_USER to user)
            return fragment
        }
    }


}