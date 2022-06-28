package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils.Constant

@Entity(tableName = Constant.NAME_OF_TIMETABLE_DETAIL_TABLE)
data class TimeTableDetail (
    @PrimaryKey
    @ColumnInfo(name = Constant.TIMETABLE_ID) val _time_table_id: String,
    @ColumnInfo(name = Constant.TIMETABLE_NAME) val _time_table_name: String
)