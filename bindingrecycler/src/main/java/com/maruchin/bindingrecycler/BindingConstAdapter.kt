package com.maruchin.bindingrecycler

import androidx.fragment.app.Fragment

abstract class BindingConstAdapter<DataType>(
    controller: Fragment,
    layoutResId: Int,
    areItemsTheSameFun: ((oldItem: DataType, newItem: DataType) -> Boolean)? = null,
    items: List<DataType>
) : BaseBindingRecyclerAdapter<DataType>(
    controller,
    layoutResId,
    areItemsTheSameFun
) {

    init {
        super.updateItemsList(items)
    }
}