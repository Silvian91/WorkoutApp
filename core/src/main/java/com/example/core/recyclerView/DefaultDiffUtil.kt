package com.example.core.recyclerView

import androidx.recyclerview.widget.DiffUtil

class DefaultDiffUtil(
    private var newList: List<com.example.core.recyclerView.ItemWrapper>,
    private var oldList: List<com.example.core.recyclerView.ItemWrapper>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition].getItemType() == newList[newItemPosition].getItemType())
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}