package com.example.passwordmanager.data

import androidx.room.*

@Dao
interface PasswordDao {

    @Insert
    suspend fun insert(password: PasswordEntity)

    @Update
    suspend fun update(password: PasswordEntity)

    @Delete
    suspend fun delete(password: PasswordEntity)

    @Query("SELECT * FROM passwords")
    suspend fun getAll(): List<PasswordEntity>
}