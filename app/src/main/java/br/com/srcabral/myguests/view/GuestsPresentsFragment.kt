package br.com.srcabral.myguests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.srcabral.myguests.R
import br.com.srcabral.myguests.databinding.FragmentAllGuestsBinding
import br.com.srcabral.myguests.databinding.FragmentPresenceBinding
import br.com.srcabral.myguests.service.constants.GuestConstants
import br.com.srcabral.myguests.view.adapter.GuestAdapter
import br.com.srcabral.myguests.view.listener.GuestListener
import br.com.srcabral.myguests.viewmodel.GuestsViewModel

class GuestsPresentsFragment : Fragment() {

    private lateinit var mListener: GuestListener
    private val mAdapter = GuestAdapter()

    private lateinit var mViewModel: GuestsViewModel
    private lateinit var binding: FragmentPresenceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel =
            ViewModelProvider(this).get(GuestsViewModel::class.java)

        binding = FragmentPresenceBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // RecycleView -> 3 etapas para usa-lo
        // 1 - Obter o RecycleView
        // root.findViewById<RecyclerView>(R.id.recycle_all_guests)
        val recycler = binding.recyclePresentsGuests

        // 2 - Definir um layout
        recycler.layoutManager = LinearLayoutManager(context)

        // 3 - Definir um adapter
        recycler.adapter = mAdapter

        // Conceito de classe anônima
        mListener = object : GuestListener{
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)

                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.PRESENT)

                Toast.makeText(context, R.string.removido_com_sucesso, Toast.LENGTH_SHORT).show()
            }
        }

        mAdapter.attachListener(mListener)
        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.PRESENT)
    }

    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.update(it)
        })
    }
}