package gam.trainingcourse.annht2_mock_final_fresherandroid_07.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constant.NAME_OF_BUDGET_TABLE)
data class Budget (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.BUDGET_ID) var budgetId: Long = 0,
    @ColumnInfo(name = Constant.BUDGET_TITLE) var budgetName: String,
    @ColumnInfo(name = Constant.BUDGET_SPENDING) var budgetConsuming: Int,
    @ColumnInfo(name = Constant.UNSELECTED_IMAGE) var unselectedImage: Int,
    @ColumnInfo(name = Constant.SELECTED_IMAGE) var selectedImage: Int,
    @ColumnInfo(name = Constant.BG_IMG) var bgImg: Int,
    @ColumnInfo(name = Constant.BACKGROUND_COLOR) var backgroundColor: Int
): Parcelable