package br.com.srcabral.myguests.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.srcabral.myguests.service.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO


    // Singleton
    companion object {
        private lateinit var instance: GuestDataBase

        fun getDataBase(context: Context): GuestDataBase {

            synchronized(GuestDataBase::class) {
                if (!::instance.isInitialized) {
                    instance = Room.databaseBuilder(context, GuestDataBase::class.java, "guestDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}