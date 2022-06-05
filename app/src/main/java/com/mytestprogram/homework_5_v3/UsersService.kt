package com.mytestprogram.homework_5_v3


import com.github.javafaker.Faker

class UsersService {
    val userList: List<User> = (1..5).map { User(
        id = it,
        name = Faker.instance().name().firstName(),
        surname = Faker.instance().name().lastName(),
        phoneNumber = Faker.instance().phoneNumber().cellPhone()
    ) }
}