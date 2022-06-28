package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.utils.Constant
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constant.NAME_OF_STUDENT_TABLE)
@Parcelize
data class Student  (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.STUDENT_ID) var student_id: Long = 0,
    @ColumnInfo(name = Constant.SCHOOL_ID) var school_id: Long,
    @ColumnInfo(name = Constant.STUDENT_NAME) var _student_name: String,
    @ColumnInfo(name = Constant.STUDENT_GRADE) var _student_grade: Int
):Parcelable