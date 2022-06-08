package com.mytestprogram.homework_6.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.mytestprogram.homework_6.R
import com.mytestprogram.homework_6.databinding.FragmentDetailsContactsBinding
import com.mytestprogram.homework_6.databinding.FragmentListContactsBinding

class DetailsContactsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsContactsBinding
    private val viewModel: DetailsContactsViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadContact(requireArguments().getLong(ARG_CONTACT_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsContactsBinding.inflate(layoutInflater, container, false)

        viewModel.contactDetails.observe(viewLifecycleOwner, Observer {
            binding.contactName.text = it.name
            binding.contactSurname.text = it.surname
            binding.contactPhoneNumber.text = it.phone
            Glide.with(binding.photoIV.context)
                .load(it.photo)
                .circleCrop()
                .into(binding.photoIV)
        })
        return binding.root
    }
    
    companion object {

        private const val ARG_CONTACT_ID = "ARG_CONTACT_ID"

        fun newInstance(id: Long): DetailsContactsFragment{
            val args = Bundle()
            
            val fragment = DetailsContactsFragment()
            fragment.arguments = bundleOf(ARG_CONTACT_ID to id)
            return fragment
        }
    }

}