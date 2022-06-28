package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.School
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.Student
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.normal.SchoolWithStudents
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.usecase.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolWithStudentsViewModel @Inject constructor(
    private val getSchoolWithStudentsUseCase: GetSchoolWithStudentsUseCase,
    private val getSchoolsUseCase: GetSchoolsUseCase,
    private val updateSchoolUseCase: UpdateSchoolUseCase,
    private val updateStudentUseCase: UpdateStudentUseCase,
    private val deleteSchoolUseCase: DeleteSchoolUseCase,
    private val deleteStudentUseCase: DeleteStudentUseCase,
    private val getCountStudents: GetCountStudents,
    private val getCountSchools: GetCountSchools
): ViewModel(){
    private val _schoolWithStudents = MutableLiveData<List<SchoolWithStudents>>()
    val mSchoolWithStudents: LiveData<List<SchoolWithStudents>>
        get() = _schoolWithStudents

    private val _schools = MutableLiveData<List<School>>()
    val mSchools: LiveData<List<School>>
        get() = _schools

    private val _countSchools = MutableLiveData<Int>()
    val mCountSchools: LiveData<Int>
        get() = _countSchools

    private val _countStudents = MutableLiveData<Int>()
    val mCountStudents: LiveData<Int>
        get() = _countStudents

    fun insertSchool(school: School){
        viewModelScope.launch {
            updateSchoolUseCase.insertSchool(school)
        }
    }

    fun insertStudent(student: Student){
        viewModelScope.launch {
            updateStudentUseCase.insertStudent(student)
        }
    }

    fun deleteSchool(school: School){
        viewModelScope.launch {
            deleteSchoolUseCase.deleteSchool(school)
        }
    }

    fun deleteStudent(student: Student){
        viewModelScope.launch {
            deleteStudentUseCase.deleteStudent(student)
        }
    }


    fun getAllSchoolWithStudents(){
        viewModelScope.launch {
            getSchoolWithStudentsUseCase.getAllSchoolWithStudents().catch {ex ->
                Log.e("Fresher_07_error", "getAllSchoolWithStudents() Exception : $ex")
                _schoolWithStudents.value = arrayListOf()
            }.collect { value1 ->
                _schoolWithStudents.value = value1
            }
        }
    }

    fun getAllSchools(){
        viewModelScope.launch {
            getSchoolsUseCase.getAllSchools().catch {ex ->
                Log.e("Fresher_07_error", "getAllSchools() Exception : $ex")
                _schools.value = arrayListOf()
            }.collect { value1 ->
                _schools.value = value1
            }
        }
    }

    fun getSchoolsNumber(){
        viewModelScope.launch {
            getCountSchools.getCountAllSchools().catch {ex ->
                Log.e("Fresher_07_error", "getStudentsNumber() Exception : $ex")
                _countSchools.value = 0
            }.collect {value ->
                _countSchools.value = value
            }
        }
    }

    fun getStudentsNumber(){
        viewModelScope.launch {
            getCountStudents.getCountAllStudents().catch {ex ->
                Log.e("Fresher_07_error", "getStudentsNumber() Exception : $ex")
                _countStudents.value = 0
            }.collect {value ->
                _countStudents.value = value
            }
        }
    }
}
