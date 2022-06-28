package gam.trainingcourse.annht2_advanceandroid_contentprovider_app_getdata

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import java.lang.IndexOutOfBoundsException


class WrapContentLinearLayoutManager : LinearLayoutManager{

    constructor(activity:Context, orientation:Int, reverseLayout: Boolean) : super(activity, orientation, reverseLayout)

    override fun onLayoutChildren(recycler: Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            Log.e("TAG", "meet a IOOBE in RecyclerView")
        }
    }

}