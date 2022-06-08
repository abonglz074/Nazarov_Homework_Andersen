package com.mytestprogram.homework_6.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mytestprogram.homework_6.models.Contact
import com.mytestprogram.homework_6.models.ContactService

class DetailsContactsViewModel(
    private val contactService: ContactService
): ViewModel() {

    private val _contactDetails = MutableLiveData<Contact>()
    val contactDetails: LiveData<Contact> = _contactDetails

    //Загружаем данные пользователя в details fragment
    fun loadContact(contactId: Long) {
        _contactDetails.value = contactService.getContactById(contactId)
    }

}