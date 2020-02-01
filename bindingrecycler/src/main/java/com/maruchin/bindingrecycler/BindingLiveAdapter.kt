package com.maruchin.bindingrecycler

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

abstract class BindingLiveAdapter<DataType>(
    controller: Fragment,
    layoutResId: Int,
    areItemsTheSameFun: ((oldItem: DataType, newItem: DataType) -> Boolean)? = null,
    itemsSource: LiveData<List<DataType>>
) : BaseBindingRecyclerAdapter<DataType>(
    controller,
    layoutResId,
    areItemsTheSameFun
) {

    init {
        itemsSource.observe(controller.viewLifecycleOwner, Observer { items ->
            super.updateItemsList(items)
        })
    }
}