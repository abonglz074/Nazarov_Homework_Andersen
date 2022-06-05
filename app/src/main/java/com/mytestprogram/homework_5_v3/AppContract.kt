package com.mytestprogram.homework_5_v3

import androidx.fragment.app.Fragment

fun Fragment.contract(): AppContract = requireActivity() as AppContract

interface AppContract {

    val usersService: UsersService

    fun launchUserDetailsScreen(user: User)
}