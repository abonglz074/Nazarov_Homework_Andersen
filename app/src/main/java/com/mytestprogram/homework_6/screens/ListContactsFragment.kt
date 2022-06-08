package com.mytestprogram.homework_6.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mytestprogram.homework_6.App
import com.mytestprogram.homework_6.ContactActionListener
import com.mytestprogram.homework_6.ContactAdapter
import com.mytestprogram.homework_6.databinding.FragmentListContactsBinding
import com.mytestprogram.homework_6.models.Contact
import com.mytestprogram.homework_6.models.ContactService

class ListContactsFragment : Fragment() {

    private lateinit var binding: FragmentListContactsBinding
    private lateinit var adapter: ContactAdapter

    private val viewModel: ListContactsViewModel by viewModels{ factory() }

    private val contactService: ContactService
        get() = (requireActivity().applicationContext as App).contactService


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListContactsBinding.inflate(inflater, container, false)

        adapter = ContactAdapter(object : ContactActionListener{
            override fun onContactDelete(contact: Contact) {
                viewModel.deleteContact(contact)
            }

            override fun onContactDetails(contact: Contact) {
                navigator().showDetails(contact)
            }
        })

        viewModel.contacts.observe(viewLifecycleOwner, Observer {
            adapter.contacts = it
        })
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root
    }

}