package com.androidpractice.budday.di

import android.content.Context
import com.androidpractice.budday.data.local.BirthdayDao
import com.androidpractice.budday.data.local.BirthdayDatabase
import com.androidpractice.budday.data.repository.BirthdayRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideBirthdayDatabase(@ApplicationContext context: Context): BirthdayDatabase {
        return BirthdayDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideBirthdayDao(database: BirthdayDatabase): BirthdayDao {
        return database.birthdayDao()
    }

    @Provides
    @Singleton
    fun provideBirthdayRepository(birthdayDao: BirthdayDao): BirthdayRepository {
        return BirthdayRepository(birthdayDao)
    }
}