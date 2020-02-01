package com.maruchin.bindingrecycler.adapters_variants

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.maruchin.bindingrecycler.AnimatedBindingRecyclerAdapter

abstract class AnimatedBindingLiveAdapter<DataType>(
    controller: Fragment,
    itemsSource: LiveData<List<DataType>>
) : AnimatedBindingRecyclerAdapter<DataType>(
    controller
) {

    init {
        itemsSource.observe(controller.viewLifecycleOwner, Observer { items ->
            super.updateItemsList(items)
        })
    }
}