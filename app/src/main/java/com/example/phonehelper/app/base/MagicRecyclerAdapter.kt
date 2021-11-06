package com.example.phonehelper.app.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.phonehelper.BR

class MagicRecyclerAdapter<LISTENER, DATA>(
    private val layout: Int,
    private val clickListener: LISTENER? = null
) : RecyclerView.Adapter<MagicRecyclerAdapter<LISTENER, DATA>.ViewHolder>() {

    private var list: MutableList<DATA> = mutableListOf()
    private var data: DATA? = null
    private lateinit var listener: (DATA) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            DataBindingUtil.inflate(
                layoutInflater,
                layout,
                parent,
                false
            ), clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(data == null) {
            holder.setData(list[position])
        } else {
            holder.setData(data!!)
        }
    }

    override fun getItemCount(): Int = if (data == null) {
        list.size
    } else {
        1
    }

    fun setData(dataList: MutableList<DATA>?) {
        if(dataList == null) { return }
        list = dataList
        notifyDataSetChanged()
    }

    fun setData(data: DATA) {
        this.data = data
        notifyDataSetChanged()
    }

    fun itemClickListener(listener: (DATA) -> Unit) {
        this.listener = listener
    }

    inner class ViewHolder(val binding: ViewDataBinding, private val clickListener: LISTENER?) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(itemData: DATA) {
            binding.setVariable(BR.item, itemData)
            if (clickListener != null) {
                binding.setVariable(BR.clickListener, clickListener)
            }
            binding.executePendingBindings()
            if (::listener.isInitialized) {
                binding.root.setOnClickListener {
                    listener.invoke(itemData)
                }
            }
        }
    }
}