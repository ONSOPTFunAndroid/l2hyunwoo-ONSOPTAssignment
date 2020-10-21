package com.example.onsoptfirstassignment.detail.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView {
    abstract class BaseAdapter<ITEM : Any, B : ViewDataBinding>(
        @LayoutRes private val layoutResId: Int,
        private val bindingVariableId: Int? = null
    ) : RecyclerView.Adapter<BaseViewHolder<B>>() {

        private val items = mutableListOf<ITEM>()

        fun replaceAll(items: List<ITEM>?) {
            items?.let {
                this.items.run {
                    clear()
                    addAll(it)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            object : BaseViewHolder<B>(
                layoutResId = layoutResId,
                parent = parent,
                bindingVariableId = bindingVariableId
            ) {}

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: BaseViewHolder<B>, position: Int) {
            holder.onBindViewHolder(items[position])
        }
    }

    abstract class BaseViewHolder<B : ViewDataBinding>(
        @LayoutRes layoutResId: Int,
        parent: ViewGroup,
        private val bindingVariableId: Int?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
    ) {

        protected val binding: B = DataBindingUtil.bind(itemView)!!

        fun onBindViewHolder(item: Any?) {
            try {
                bindingVariableId?.let {
                    binding.setVariable(it, item)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}