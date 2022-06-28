package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.adapter

import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.normal.SchoolWithStudents

interface IDeleteSchool {
    fun deleteSchoolListener(schoolWithStudents: SchoolWithStudents)
}