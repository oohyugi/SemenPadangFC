package com.spfc.oohyugi.semenpadangfc.commons

import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

/**
 * Created by oohyugi on 8/16/17.
 */
abstract class InfiniteNestedScrollListener(
        val layoutManager: LinearLayoutManager) : NestedScrollView.OnScrollChangeListener {
    var currentPage = 0
    // The total number of items in the dataset after the last load
    var previousTotalItemCount = 0

    // Sets the starting page index
     val startingPageIndex = 0
    // The minimum amount of pixels to have below your current scroll position
    // before loading more.
    private val visibleThresholdDistance = 300
    override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        val view = v?.getChildAt(v?.getChildCount() - 1)
        val distanceToEnd = view!!.getBottom() - (v.getHeight() + v.getScrollY())

        val totalItemCount = layoutManager.getItemCount()
        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && distanceToEnd <= visibleThresholdDistance) {
            currentPage++
            onLoadMore(currentPage, totalItemCount)
            loading = true
        }
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int)

    private var loading = true



}