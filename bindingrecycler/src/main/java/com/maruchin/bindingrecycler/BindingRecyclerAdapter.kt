package com.maruchin.bindingrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

abstract class BindingRecyclerAdapter<DataType> private constructor(
    private val controller: Fragment
) : RecyclerView.Adapter<BindingViewHolder<DataType>>() {

    private val itemsList = mutableListOf<DataType>()

    constructor(controller: Fragment, items: List<DataType>) : this(controller) {
        updateList(items)
    }

    constructor(controller: Fragment, itemsSource: LiveData<List<DataType>>) : this(controller) {
        itemsSource.observe(controller.viewLifecycleOwner, Observer { items ->
            updateList(items)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<DataType> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return BindingViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: BindingViewHolder<DataType>, position: Int) {
        val item = itemsList[position]
        holder.bind(position, item, controller)
        onBindViewHolder(holder)
    }

    protected abstract fun getLayoutIdForPosition(position: Int): Int

    protected abstract fun onBindViewHolder(holder: BindingViewHolder<DataType>)

    private fun updateList(newList: List<DataType>) {
        itemsList.clear()
        itemsList.addAll(newList)
        notifyDataSetChanged()
    }
}