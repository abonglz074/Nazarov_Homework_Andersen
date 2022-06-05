package com.mytestprogram.homework_5_v3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), AppContract {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, ListContactsFragment())
                .commit()
        }
    }

    override val usersService: UsersService
        get() = (applicationContext as App).usersService

    override fun launchUserDetailsScreen(user: User) {
        val fragment = DetailsContactFragment.newInstance(user)
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}