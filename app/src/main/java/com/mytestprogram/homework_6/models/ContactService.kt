package com.mytestprogram.homework_6.models

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.github.javafaker.Faker
import com.mytestprogram.homework_6.R

//ContactService всегда оповещает этого слушателя, если его добавить с помощью метода addListener
typealias ContactsListener = (contacts: List<Contact>) -> Unit

// Класс для управления данными (получение, удаление и т.д.)
class ContactService {

    private var contacts = mutableListOf<Contact>()

    private val listeners = mutableSetOf<ContactsListener>()

    // Инициализация списка контактов случайными значениями
    init {
        contacts = (1..100).map { Contact (
            id = it.toLong(),
            name = Faker.instance().name().firstName(),
            surname = Faker.instance().name().lastName(),
            phone = Faker.instance().phoneNumber().cellPhone(),
            photo = "https://picsum.photos/id/${(1..100).random()}/64/64"
                )
        }.toMutableList()
    }

    fun getContacts(): List<Contact> {
        return contacts
    }

    fun getContactById(id: Long): Contact {
        val index = contacts.indexOfFirst{ it.id == id }
        return contacts[index]
    }

    fun deleteUser(contact: Contact) {
        val indexToDelete = contacts.indexOfFirst { it.id == contact.id }
        if (indexToDelete != -1) {
            contacts.removeAt(indexToDelete)
        }
        notifyChanges()
    }


    fun addListener(listener: ContactsListener) {
        listeners.add(listener)
        listener.invoke(contacts)
    }

    fun removeListener(listener: ContactsListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach{ it.invoke(contacts)}
    }


}