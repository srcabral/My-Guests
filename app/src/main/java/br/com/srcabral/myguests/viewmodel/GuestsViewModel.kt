package br.com.srcabral.myguests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.srcabral.myguests.service.model.GuestModel
import br.com.srcabral.myguests.service.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository.getInstance(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int) {
        if (filter == 0) {
            mGuestList.value = mGuestRepository.getAbsent()
        } else if (filter == 1) {
            mGuestList.value = mGuestRepository.getPresent()
        } else {
            mGuestList.value = mGuestRepository.getAll()
        }
    }

    fun delete(id: Int) {
        mGuestRepository.delete(id)
    }
}