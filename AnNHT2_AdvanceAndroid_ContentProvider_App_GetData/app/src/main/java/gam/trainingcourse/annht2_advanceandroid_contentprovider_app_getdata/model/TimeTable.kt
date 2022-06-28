package gam.trainingcourse.annht2_advanceandroid_contentprovider_app_getdata.model

data class TimeTable  (
    var _time_table_id: String,
    var _time_table_name: String,
    var _day_in_week: String,
    var _subjects: String,
    var _pm_key:Long = 0
)