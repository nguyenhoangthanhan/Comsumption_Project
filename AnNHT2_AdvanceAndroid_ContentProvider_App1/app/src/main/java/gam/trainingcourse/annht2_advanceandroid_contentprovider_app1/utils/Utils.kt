package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils

import android.content.ContentValues
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.entity.TimeTable

object Utils {
    fun fromContentValues(values: ContentValues?): TimeTable {
        var _time_table_id = ""
        var _time_table_name = ""
        var _day_in_week = ""
        var _subjects = ""
        var _pm_key = -1

        if (values != null && values.containsKey(Constant.TIMETABLE_ID)) {
            _time_table_id = values.getAsString(Constant.TIMETABLE_ID)
        }
        if (values != null && values.containsKey(Constant.TIMETABLE_NAME)) {
            _time_table_name = values.getAsString(Constant.TIMETABLE_NAME)
        }
        if (values != null && values.containsKey(Constant.DAY_IN_WEEK)) {
            _day_in_week = values.getAsString(Constant.DAY_IN_WEEK)
        }
        if (values != null && values.containsKey(Constant.SUBJECTS)) {
            _subjects = values.getAsString(Constant.SUBJECTS)
        }
        if (values != null && values.containsKey("_pm_key")) {
            _pm_key = values.getAsInteger("_pm_key")
        }

        return TimeTable(
            _time_table_id = _time_table_id,
            _time_table_name = _time_table_name,
            _day_in_week = _day_in_week,
            _subjects = _subjects
        )
    }
}