package gam.trainingcourse.annht2_advanceandroid_contentprovider_app_getdata.utils

import android.media.tv.TvContract.AUTHORITY
import android.net.Uri

object Constant {
    const val DATABASE_NAME = "TimeTable DataBase"
    const val NAME_OF_TIMETABLE_DETAIL_TABLE = "timetable_detail_table_name"
    const val TIMETABLE_ID = "timetable_id"
    const val TIMETABLE_NAME = "timetable_name"
    const val DAY_IN_WEEK = "day_in_week"
    const val SUBJECTS = "subjects"

    const val NAME_OF_TIMETABLE_TABLE = "timetable_table_name"
    const val AUTHORITY = "gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.timetable_provider"
    val URI_SUBJECT: Uri = Uri.parse("content://$AUTHORITY/$NAME_OF_TIMETABLE_TABLE")


    const val MONDAY = "Monday"
    const val TUESDAY = "Tuesday"
    const val WEDNESDAY = "Wednesday"
    const val THURSDAY = "Thursday"
    const val FRIDAY = "Friday"
    const val SATURDAY = "Saturday"
    const val SUNDAY = "Sunday"

}