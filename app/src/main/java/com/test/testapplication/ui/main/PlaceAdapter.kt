package com.test.testapplication.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.testapplication.R
import com.test.testapplication.base.BaseRecyclerViewAdapter
import com.test.testapplication.data.model.Place
import com.test.testapplication.databinding.RecyclerItemListBinding

class PlaceAdapter : BaseRecyclerViewAdapter<Place, RecyclerItemListBinding>() {

    override val layoutId: Int
        get() = R.layout.recycler_item_list


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerItemViewHolder(createBindedView(viewGroup))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = items[position]

        val viewHolder = holder as RecyclerItemViewHolder
        viewHolder.binding.tvText.text = data.placeName
    }

    inner class RecyclerItemViewHolder(val binding: RecyclerItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}