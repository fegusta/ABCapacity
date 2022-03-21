package com.fegusta.abcapacity.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.fegusta.abcapacity.JogoActivity
import com.fegusta.abcapacity.R
import com.fegusta.abcapacity.constants.Constants
import com.fegusta.abcapacity.data.model.Level

class BaseAdapter<T: BaseViewHolder<U>, U>(
        private val viewHolderLaunch: (ViewGroup) -> T ) : RecyclerView.Adapter<T>() {

    var items: MutableList<U> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return viewHolderLaunch(parent)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

abstract class BaseViewHolder<U>(@LayoutRes layout: Int, viewGroup: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
) {
    abstract fun bind(item: U)

}