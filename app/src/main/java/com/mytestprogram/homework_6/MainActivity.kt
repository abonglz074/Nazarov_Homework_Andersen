package com.mytestprogram.homework_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mytestprogram.homework_6.databinding.ActivityMainBinding
import com.mytestprogram.homework_6.models.Contact
import com.mytestprogram.homework_6.screens.DetailsContactsFragment
import com.mytestprogram.homework_6.screens.ListContactsFragment

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, ListContactsFragment())
                .commit()
        }
    }

    override fun showDetails(contact: Contact) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, DetailsContactsFragment.newInstance(contact.id))
            .commit()
    }

    override fun goBack() {
        onBackPressed()
    }
}