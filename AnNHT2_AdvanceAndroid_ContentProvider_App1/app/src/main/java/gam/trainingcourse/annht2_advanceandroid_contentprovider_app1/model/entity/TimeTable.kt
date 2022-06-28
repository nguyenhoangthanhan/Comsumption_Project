package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils.Constant
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constant.NAME_OF_TIMETABLE_TABLE)
@Parcelize
data class TimeTable  (
    @ColumnInfo(name = Constant.TIMETABLE_ID) var _time_table_id: String,
    @ColumnInfo(name = Constant.TIMETABLE_NAME) var _time_table_name: String,
    @ColumnInfo(name = Constant.DAY_IN_WEEK) var _day_in_week: String,
    @ColumnInfo(name = Constant.SUBJECTS) var _subjects: String,
    @PrimaryKey(autoGenerate = true) var _pm_key:Long = 0
):Parcelable