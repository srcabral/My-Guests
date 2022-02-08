package br.com.srcabral.myguests.service.repository

import android.content.ContentValues
import android.content.Context
import br.com.srcabral.myguests.service.constants.DataBaseConstants
import br.com.srcabral.myguests.service.model.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context) {

    private var mGuestDataBaseHelper = GuestDataBaseHelper(context)

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun save(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun update(guest: GuestModel) {
        val db = mGuestDataBaseHelper.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
        contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

        val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
        val args = arrayOf(guest.id.toString())

        db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)
    }

    fun delete(guest: GuestModel) {

    }
}