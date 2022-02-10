package br.com.srcabral.myguests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.srcabral.myguests.viewmodel.GuestFormViewModel
import br.com.srcabral.myguests.R
import br.com.srcabral.myguests.databinding.ActivityGuestFormBinding
import br.com.srcabral.myguests.service.constants.GuestConstants
import br.com.srcabral.myguests.service.model.GuestModel

class GuestFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var mViewModel: GuestFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        binding.radioPresence.isChecked = true
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            mViewModel.load(mGuestId)
        }
    }

    /*override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.button_salve) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresence.isChecked
            mViewModel.save(name, presence)
        }
    }*/

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
            if (it) {
                if (mGuestId == 0) {
                    Toast.makeText(applicationContext, "Salvo!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Atualizado!", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (mGuestId == 0) {
                    Toast.makeText(applicationContext, "Falha ao salvar!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(applicationContext, "Falha ao atualizar!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            finish()
        })
        mViewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresence.isChecked = true
            } else {
                binding.radioAbsence.isChecked = true
            }
        })
    }

    private fun setListeners() {
        binding.buttonSalve.setOnClickListener {
            val id = it.id
            if (id == R.id.button_salve) {
                val name = binding.editName.text.toString()
                val presence = binding.radioPresence.isChecked
                mViewModel.save(mGuestId, name, presence)
            }
        }
    }
}