package com.vokrob.botanists_handbook.di

import android.app.Application
import androidx.room.Room
import com.vokrob.botanists_handbook.db.MainDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDb {
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "botan.db"
        ).createFromAsset("db/botan.db").build()
    }
}























