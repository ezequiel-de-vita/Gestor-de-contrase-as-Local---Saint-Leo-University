package com.example.passwordmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PasswordEntity::class, UserEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun passwordDao(): PasswordDao
    abstract fun userDao(): UserDao
}