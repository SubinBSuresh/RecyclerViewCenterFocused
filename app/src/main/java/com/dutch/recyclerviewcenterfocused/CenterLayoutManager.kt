package com.dutch.recyclerviewcenterfocused

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager

class CenterLayoutManager(context: Context) : LinearLayoutManager(context) {

    private val TAG = "DUTCH_"
    override fun scrollToPositionWithOffset(position: Int, offset: Int) {
        Log.i(TAG, "scrollToPositionWithOffset, position: $position, offset: $offset")
        super.scrollToPositionWithOffset(position, offset)
    }

    fun scrollToCenter(position: Int) {
        val recyclerCenter = height / 2
        scrollToPositionWithOffset(position, recyclerCenter - getItemHeight() / 2)
    }

    private fun getItemHeight(): Int {
        return 100 //get item height or get from viewholder
    }
}