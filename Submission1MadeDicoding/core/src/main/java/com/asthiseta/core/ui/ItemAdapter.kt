package com.asthiseta.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asthiseta.core.databinding.ListItemLayoutBinding
import com.asthiseta.core.domain.model.Item

class ItemAdapter(private val item: ArrayList<Item>, private val clickListener :(String, View) -> Unit):
RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    fun setData(items : List<Item>?){
        item.apply {
            clear()
            items?.let { addAll(it) }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(ListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int)  = holder.bind(item[position], clickListener)

    override fun getItemCount(): Int {
        return item.size
    }

    inner class ItemViewHolder(private val binding : ListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(item : Item, click : (String, View)-> Unit){
                binding.apply {
                    data = item

                    root.transitionName = item.name
                    root.setOnClickListener { item.name?.let { it1 -> click(it1, root) } }
                }
            }
    }

}