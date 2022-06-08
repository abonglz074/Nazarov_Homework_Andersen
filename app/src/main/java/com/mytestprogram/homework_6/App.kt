package com.mytestprogram.homework_6

import android.app.Application
import com.mytestprogram.homework_6.models.ContactService

//Создаем singleton экземпляр класса
// (чтобы из любого места нашего приложения можно было получать доступ к классу ContactService
class App : Application() {

    val contactService = ContactService()
}