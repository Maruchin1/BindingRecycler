package com.maruchin.bindingrecycler.adapters_variants

import androidx.fragment.app.Fragment
import com.maruchin.bindingrecycler.AnimatedBindingRecyclerAdapter

abstract class AnimatedBindingConstAdapter<DataType>(
    controller: Fragment,
    items: List<DataType>
) : AnimatedBindingRecyclerAdapter<DataType>(controller) {

    init {
        super.updateItemsList(items)
    }
}