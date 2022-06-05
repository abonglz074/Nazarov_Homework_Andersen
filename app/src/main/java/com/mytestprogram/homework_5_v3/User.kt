package com.mytestprogram.homework_5_v3


import java.io.Serializable

data class User(
    val id: Int,
    val name: String,
    val surname: String,
    val phoneNumber: String
): Serializable{
    override fun toString(): String {
        return "$name $surname $phoneNumber"
    }
}

