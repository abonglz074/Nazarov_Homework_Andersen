package com.mytestprogram.homework_6.screens

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mytestprogram.homework_6.App
import com.mytestprogram.homework_6.Navigator

//Задача фабрики вьюмоделей создавать вьюмодели с нужными параметрами, которые передаются в конструктор
class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            ListContactsViewModel::class.java -> {
                ListContactsViewModel(app.contactService)
            }
            DetailsContactsViewModel::class.java -> {
                DetailsContactsViewModel(app.contactService)
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}

//Extension method, который можно будет вызвать из любого фрагмента
fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)

fun Fragment.navigator() = requireActivity() as Navigator