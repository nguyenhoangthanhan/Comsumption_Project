package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.view.activity

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.R
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.database.TimeTableDatabase
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.timetable_provider.TimeTableProvider
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                val c: Cursor? =
                    contentResolver.query(TimeTableProvider.URI_TIME_TABLE
                        , null, null, null, null)
//                adapter.mCursor = c
                Log.d("AAAAA", c?.count.toString())
            }
//            recyclerView.adapter = adapter
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
