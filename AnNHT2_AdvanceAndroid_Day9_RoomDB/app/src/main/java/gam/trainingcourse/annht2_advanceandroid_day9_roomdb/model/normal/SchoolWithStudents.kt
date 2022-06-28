package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.normal

import androidx.room.Embedded
import androidx.room.Relation
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.School
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.Student
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.utils.Constant

class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        parentColumn = Constant.SCHOOL_ID,
        entityColumn = Constant.SCHOOL_ID
    )
    val listStudents: List<Student>
)