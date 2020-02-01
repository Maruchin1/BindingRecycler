package com.maruchin.bindingrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseBindingRecyclerAdapter<DataType>(
    private val controller: Fragment
) : RecyclerView.Adapter<BindingViewHolder<DataType>>() {

    protected val itemsList = mutableListOf<DataType>()

    open fun updateItemsList(newList: List<DataType>) {
        hardUpdateList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<DataType> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return BindingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: BindingViewHolder<DataType>, position: Int) {
        val item = itemsList[position]
        holder.bind(
            position = position,
            item = item,
            controller = controller
        )
        onBindViewHolder(holder)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getLayoutIdForPosition(position: Int): Int

    protected abstract fun onBindViewHolder(holder: BindingViewHolder<DataType>)

    private fun hardUpdateList(newList: List<DataType>) {
        itemsList.clear()
        itemsList.addAll(newList)
        notifyDataSetChanged()
    }
}