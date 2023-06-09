package com.sample.character.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*

class VerticalMarginDecorator(private val spaceSize: Int) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceSize
            }
            left = spaceSize
            right = spaceSize
            bottom = spaceSize
        }
    }
}