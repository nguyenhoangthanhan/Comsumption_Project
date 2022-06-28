package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.timetable_provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.database.TimeTableDatabase
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils.Constant
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils.Utils

class TimeTableProvider : ContentProvider() {

    /** The URI for a table.  */
    companion object {
        const val AUTHORITY = "gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.timetable_provider"
        val URI_TIME_TABLE = Uri.parse(
            "content://" + AUTHORITY + "/" + Constant.NAME_OF_TIMETABLE_TABLE)

        private const val CODE_TIMETABLE_DIR = 1

        private const val CODE_TIMETABLE_ITEM = 2
        private val MATCHER: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            MATCHER.addURI(AUTHORITY, Constant.NAME_OF_TIMETABLE_TABLE, CODE_TIMETABLE_DIR)
            MATCHER.addURI(AUTHORITY, Constant.NAME_OF_TIMETABLE_TABLE + "/*", CODE_TIMETABLE_ITEM)
        }

    }

    override fun onCreate(): Boolean {
        Log.d("ContentProvi_logAUTHORITY", AUTHORITY)
        Log.d("ContentProvi_logNAME_OF_TIMETABLE_TABLE", Constant.NAME_OF_TIMETABLE_TABLE)
        Log.d("ContentProvi_logCODE_TIMETABLE_DIR", CODE_TIMETABLE_DIR.toString())
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        Log.d("ContentProvi_log", "query")
        val code = MATCHER.match(uri)
        if (code == CODE_TIMETABLE_DIR || code == CODE_TIMETABLE_ITEM){
            if (context == null){
                Log.d("ContentProvi_log", "ContentProvi_log query return null")
                return null
            }
            val timeTableDao = TimeTableDatabase.getInstance(context!!)?.timeTableDao()
            val cursor:Cursor? = if (code == CODE_TIMETABLE_DIR){
                timeTableDao.getAllReturnCursor()
            }
            else{
                timeTableDao.getAllReturnCursor()
            }
            cursor?.setNotificationUri(context!!.contentResolver, uri)
            return cursor
        }else {
            throw  IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String {
        return when (MATCHER.match(uri)) {
            CODE_TIMETABLE_DIR -> "vnd.android.cursor.dir/" + AUTHORITY + "." + Constant.NAME_OF_TIMETABLE_TABLE
            CODE_TIMETABLE_ITEM -> "vnd.android.cursor.item/" + AUTHORITY + "." + Constant.NAME_OF_TIMETABLE_TABLE
            else -> {
                throw java.lang.IllegalArgumentException("Unknown URI: $uri")
            }
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (MATCHER.match(uri)) {
            CODE_TIMETABLE_DIR -> {
                if (context == null)
                    return null
                val id = TimeTableDatabase.getInstance(context!!).timeTableDao()
                    .insertFromContentProvider(Utils.fromContentValues(values))
                context?.contentResolver?.notifyChange(uri, null)

                return id.let { ContentUris.withAppendedId(uri, it) }
            }
            CODE_TIMETABLE_ITEM ->
                throw java.lang.IllegalArgumentException("Invalid URI, cannot insert with ID: $uri")
            else ->
                throw java.lang.IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        when (MATCHER.match(uri)) {
            CODE_TIMETABLE_DIR ->
                throw java.lang.IllegalArgumentException("Invalid URI, cannot delete without ID")
            CODE_TIMETABLE_ITEM -> {
                if (context == null)
                    return 0
                val count = TimeTableDatabase.getInstance(context!!).timeTableDao()
                    .deleteById(ContentUris.parseId(uri))
                context!!.contentResolver.notifyChange(uri, null)
                return count

            }
            else ->
                throw java.lang.IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        when (MATCHER.match(uri)) {
            CODE_TIMETABLE_DIR ->
                throw java.lang.IllegalArgumentException("Invalid URI, cannot update without ID")
            CODE_TIMETABLE_ITEM -> {
                if (context == null)
                    return 0
                val timeTable = Utils.fromContentValues(values)
                timeTable._pm_key = ContentUris.parseId(uri)
                val count = TimeTableDatabase.getInstance(context!!).timeTableDao().update(timeTable)
                context!!.contentResolver.notifyChange(uri, null)
                return count
            }
            else ->
                throw java.lang.IllegalArgumentException("Unknown URI: $uri")
        }
    }
}