package com.androidpractice.budday.data.local

import androidx.room.*
import com.androidpractice.budday.data.model.Birthday
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface BirthdayDao {
    @Query("SELECT * FROM birthdays")
    fun getAllBirthdays(): Flow<List<Birthday>>

    @Query("SELECT * FROM birthdays WHERE id = :id")
    fun getBirthdayById(id: Int): Flow<Birthday?>

    @Query("""
        SELECT * FROM birthdays 
        WHERE strftime('%m', birthDate) = :month 
        ORDER BY strftime('%d', birthDate)
    """)
    fun getBirthdaysByMonth(month: String): Flow<List<Birthday>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBirthday(birthday: Birthday): Long

    @Update
    suspend fun updateBirthday(birthday: Birthday)

    @Delete
    suspend fun deleteBirthday(birthday: Birthday)
}