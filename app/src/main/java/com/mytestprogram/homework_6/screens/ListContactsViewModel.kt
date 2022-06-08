package com.mytestprogram.homework_6.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mytestprogram.homework_6.models.Contact
import com.mytestprogram.homework_6.models.ContactService
import com.mytestprogram.homework_6.models.ContactsListener

class ListContactsViewModel(
    private val contactService: ContactService
): ViewModel() {

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts

    private val listener: ContactsListener = {
        _contacts.value = it
    }

    init {
        loadContacts()
    }

    override fun onCleared() {
        super.onCleared()
        contactService.removeListener(listener)
    }
    fun loadContacts() {
        contactService.addListener(listener)
    }

    fun deleteContact(contact: Contact) {
        contactService.deleteUser(contact)
    }




}