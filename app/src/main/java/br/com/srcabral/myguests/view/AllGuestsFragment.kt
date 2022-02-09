package br.com.srcabral.myguests.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.srcabral.myguests.databinding.FragmentAllGuestsBinding
import br.com.srcabral.myguests.view.adapter.GuestAdapter
import br.com.srcabral.myguests.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private lateinit var binding: FragmentAllGuestsBinding
    private val mAdapter = GuestAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // RecycleView -> 3 etapas para usa-lo
        // 1 - Obter o RecycleView
        // root.findViewById<RecyclerView>(R.id.recycle_all_guests)
        val recycler = binding.recycleAllGuests

        // 2 - Definir um layout
        recycler.layoutManager = LinearLayoutManager(context)

        // 3 - Definir um adapter
        recycler.adapter = mAdapter

        observer()

        allGuestsViewModel.load()
        return root
    }

    private fun observer() {
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.update(it)
        })
    }
}