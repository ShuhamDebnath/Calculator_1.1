package com.example.calculator11.di

import android.app.Application
import androidx.room.Room
import com.example.calculator11.data.local.HistoryDao
import com.example.calculator11.data.local.CalculatorDatabase
import com.example.calculator11.data.repositoryImpl.CalculatorRepositoryImpl
import com.example.calculator11.domain.repository.CalculatorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesDatabase(context: Application): CalculatorDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CalculatorDatabase::class.java,
            "history_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideHistoryDao(database: CalculatorDatabase): HistoryDao {
        return database.historyDao()
    }

    @Provides
    @Singleton
    fun provideCalculatorRepository(
        historyDao: HistoryDao
    ): CalculatorRepository {
        return CalculatorRepositoryImpl(historyDao)
    }


}