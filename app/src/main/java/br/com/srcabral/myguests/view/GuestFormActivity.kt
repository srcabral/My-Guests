package br.com.srcabral.myguests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.srcabral.myguests.viewmodel.GuestFormViewModel
import br.com.srcabral.myguests.R
import br.com.srcabral.myguests.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()

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
                Toast.makeText(applicationContext, "Sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha!", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
    }

    private fun setListeners() {
        binding.buttonSalve.setOnClickListener {
            val id = it.id
            if (id == R.id.button_salve){
                val name = binding.editName.text.toString()
                val presence = binding.radioPresence.isChecked
                mViewModel.save(name, presence)
            }
        }
    }


}