package com.maruchin.bindingrecycler

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil

abstract class AnimatedBindingRecyclerAdapter<DataType>(
    controller: Fragment
) : BaseBindingRecyclerAdapter<DataType>(controller) {

    override fun updateItemsList(newList: List<DataType>) {
        diffUpdateList(newList)
    }

    protected abstract fun areItemsTheSame(oldItem: DataType, newItem: DataType): Boolean

    private fun diffUpdateList(newList: List<DataType>) {
        val diffCallback = MyDiffCallback(this::areItemsTheSame)
        diffCallback.setComparedLists(
            oldList = itemsList,
            newList = newList
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        itemsList.clear()
        itemsList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}