package com.androidpractice.budday.data.repository

import com.androidpractice.budday.data.local.BirthdayDao
import com.androidpractice.budday.data.model.Birthday
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BirthdayRepository @Inject constructor(
    private val birthdayDao: BirthdayDao
) {
    fun getAllBirthdays(): Flow<List<Birthday>> = birthdayDao.getAllBirthdays()

    fun getBirthdayById(id: Int): Flow<Birthday?> = birthdayDao.getBirthdayById(id)

    fun getBirthdaysByMonth(month: String): Flow<List<Birthday>> =
        birthdayDao.getBirthdaysByMonth(month)

    suspend fun addBirthday(birthday: Birthday): Long =
        birthdayDao.insertBirthday(birthday)

    suspend fun updateBirthday(birthday: Birthday) =
        birthdayDao.updateBirthday(birthday)

    suspend fun deleteBirthday(birthday: Birthday) =
        birthdayDao.deleteBirthday(birthday)
}