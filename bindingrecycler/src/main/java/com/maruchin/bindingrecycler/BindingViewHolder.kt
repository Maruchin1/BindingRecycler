package com.maruchin.bindingrecycler

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    val bindingView: View
        get() = binding.root

    fun bind(item: Any, controller: Any) {
        binding.apply {
            setVariable(BR.item, item)
            setVariable(BR.controller, controller)
        }
    }
}