package com.maruchin.bindingrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseBindingRecyclerAdapter<DataType>(
    private val controller: Fragment,
    private val layoutResId: Int,
    areItemsTheSameFun: ((oldItem: DataType, newItem: DataType) -> Boolean)? = null
) : RecyclerView.Adapter<BindingViewHolder<DataType>>() {

    private val itemsList = mutableListOf<DataType>()
    private val diffCallback: MyDiffCallback<DataType>?

    init {
        if (areItemsTheSameFun != null) {
            diffCallback = MyDiffCallback(areItemsTheSameFun)
        } else {
            diffCallback = null
        }
    }

    fun updateItemsList(newList: List<DataType>) {
        if (diffCallback == null) {
            hardUpdateList(newList)
        } else {
            diffUpdateList(newList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<DataType> {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutResId,
            parent,
            false
        )
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

    abstract fun onBindViewHolder(holder: BindingViewHolder<DataType>)

    private fun hardUpdateList(newList: List<DataType>) {
        itemsList.clear()
        itemsList.addAll(newList)
        notifyDataSetChanged()
    }

    private fun diffUpdateList(newList: List<DataType>) {
        diffCallback!!.setComparedLists(
            oldList = itemsList,
            newList = newList
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        itemsList.clear()
        itemsList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}