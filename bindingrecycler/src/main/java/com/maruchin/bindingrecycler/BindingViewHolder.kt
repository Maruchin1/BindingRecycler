package com.maruchin.bindingrecycler

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder<DataType>(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    val bindingView: View
        get() = binding.root

    fun bind(position: Int, item: DataType, controller: Fragment) {
        binding.apply {
            setVariable(BR.position, position)
            setVariable(BR.item, item)
            setVariable(BR.controller, controller)
        }
    }
}