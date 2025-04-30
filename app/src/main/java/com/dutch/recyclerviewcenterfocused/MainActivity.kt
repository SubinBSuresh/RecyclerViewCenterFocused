package com.dutch.recyclerviewcenterfocused

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mListAdapter: ListAdapter
    private var currentIndex = 0
    private lateinit var layoutManager: CenterLayoutManager
    private val TAG = "DUTCH__"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById<RecyclerView>(R.id.rv_list)
        layoutManager = CenterLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager
        mListAdapter = ListAdapter(List(21) { "Item $it" })
        mRecyclerView.adapter = mListAdapter
        mRecyclerView.post {
            Log.i(TAG, "recycler view post")
            mRecyclerView.findViewHolderForAdapterPosition(0)?.itemView?.requestFocus()
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.i(TAG, "onKeyDown() keycode: $keyCode currentIndex: $currentIndex")
//        val layoutManager = mRecyclerView.layoutManager as CenterLayoutManager
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                //adb shell input keyevent KEYCODE_DPAD_DOWN
                val nextIndex = (currentIndex + 1).coerceAtMost(mListAdapter.itemCount - 1)
                layoutManager.scrollToCenter(nextIndex)
                mRecyclerView.post {
                    mRecyclerView.findViewHolderForAdapterPosition(nextIndex)?.itemView?.requestFocus()
                }
                currentIndex = nextIndex
                return true
            }

            KeyEvent.KEYCODE_DPAD_UP -> {
//                adb shell input keyevent KEYCODE_DPAD_UP
                val prevIndex = (currentIndex - 1).coerceAtLeast(0)
                layoutManager.scrollToCenter(prevIndex)
                mRecyclerView.post {
                    mRecyclerView.findViewHolderForAdapterPosition(prevIndex)?.itemView?.requestFocus()
                }
                currentIndex = prevIndex
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }


    inner class CenterSmoothScroller(context: Context) : LinearSmoothScroller(context) {
        override fun getVerticalSnapPreference(): Int = SNAP_TO_START

        override fun calculateDyToMakeVisible(view: View, snapPreference: Int): Int {
            val parentCenter = mRecyclerView.height / 2
            val childCenter = view.top + view.height / 2
            return childCenter - parentCenter
        }
    }


}