package com.example.onsoptfirstassignment.detail.recyclerview

import android.content.Context
import android.graphics.Canvas
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors.ALPHA_FULL

class ItemTouchHelperCallback(private val context : Context,
                              adapter: ProjectAdapter) : ItemTouchHelper.Callback() {
    private val mAdapter = adapter

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        if(recyclerView.layoutManager == GridLayoutManager(context, 3)) {
            val dragFlags =
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = 0
            return makeMovementFlags(dragFlags, swipeFlags)
        } else {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            return makeMovementFlags(dragFlags, swipeFlags)
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if(viewHolder.itemViewType != target.itemViewType)
            return false

        mAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        mAdapter.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val alpha = ALPHA_FULL - Math.abs(dX) / viewHolder.itemView.width
            viewHolder.itemView.apply {
                setAlpha(alpha)
                translationX = dX
            }
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

}