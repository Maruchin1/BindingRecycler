package com.maruchin.bindingrecycler.adapters_variants

import androidx.fragment.app.Fragment
import com.maruchin.bindingrecycler.BaseBindingRecyclerAdapter

abstract class BindingConstAdapter<DataType>(
    controller: Fragment,
    items: List<DataType>
) : BaseBindingRecyclerAdapter<DataType>(controller) {

    init {
        super.updateItemsList(items)
    }
}