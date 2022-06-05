package com.mytestprogram.homework_5_v3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.github.javafaker.Faker
import com.mytestprogram.homework_5_v3.databinding.FragmentListContactsBinding
import kotlinx.coroutines.currentCoroutineContext

class ListContactsFragment: Fragment() {

    private lateinit var binding: FragmentListContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListContactsBinding.inflate(inflater, container, false)

        val users = contract().usersService.userList
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, users)
        binding.listContacts.adapter = adapter
        binding.listContacts.setOnItemClickListener { _, _, position, _ ->

            val currentUser = adapter.getItem(position)!!
            contract().launchUserDetailsScreen(currentUser)
        }
        return binding.root
    }
}