package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.utils.Constant

@Entity(tableName = Constant.NAME_OF_SCHOOL_TABLE)
data class School (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.SCHOOL_ID) var school_id: Long = 0,
    @ColumnInfo(name = Constant.SCHOOL_NAME) var _school_name: String,
    @ColumnInfo(name = Constant.SCHOOL_ADDRESS) var _school_address: String
){
    override fun toString(): String {
        return this._school_name
    }
}