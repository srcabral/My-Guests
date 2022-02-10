package br.com.srcabral.myguests.service.repository

import android.content.ContentValues
import android.content.Context
import androidx.core.database.getStringOrNull
import br.com.srcabral.myguests.service.constants.DataBaseConstants
import br.com.srcabral.myguests.service.model.GuestModel
import java.lang.Exception

class GuestRepository (context: Context) {

    // Acessa ao banco de dados
    private val mDataBase = GuestDataBase.getDataBase(context).guestDAO()

    fun get(id: Int): GuestModel {
        return mDataBase.get(id)
    }

    fun save(guest: GuestModel): Boolean {
        return mDataBase.save(guest) > 0
    }

    fun getAll(): List<GuestModel> {
        return mDataBase.getInvited()
    }

    fun getPresent(): List<GuestModel> {
        return mDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return mDataBase.getAbsent()
    }

    fun update(guest: GuestModel): Boolean {
        return mDataBase.update(guest) > 0
    }

    fun delete(guest: GuestModel) {
        mDataBase.delete(guest)
    }
}