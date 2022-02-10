package br.com.srcabral.myguests.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.srcabral.myguests.R
import br.com.srcabral.myguests.service.model.GuestModel
import br.com.srcabral.myguests.view.listener.GuestListener
import br.com.srcabral.myguests.view.viewholder.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    private var mGuestList: List<GuestModel> = arrayListOf()
    private lateinit var mListener: GuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(mGuestList[position])
    }

    override fun getItemCount(): Int {
        return mGuestList.count()
    }

    fun update(list: List<GuestModel>){
        mGuestList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: GuestListener){
        mListener = listener
    }
}