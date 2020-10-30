package com.example.onsoptfirstassignment.detail.recyclerview

interface ItemTouchHelperAdapter {
    fun onItemMove(from: Int, to: Int) : Boolean
    fun onItemDismiss(position: Int)
}