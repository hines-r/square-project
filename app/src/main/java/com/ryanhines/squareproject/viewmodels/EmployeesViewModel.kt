package com.ryanhines.squareproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryanhines.squareproject.models.Employee
import com.ryanhines.squareproject.network.ApiClient
import com.ryanhines.squareproject.repository.EmployeeRepository
import com.ryanhines.squareproject.util.ScreenState
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeesViewModel(
    private val repository: EmployeeRepository = EmployeeRepository(ApiClient.apiService)
) : ViewModel() {

    private val _employeesLiveData = MutableLiveData<ScreenState<List<Employee>?>>()

    val employeeLiveData: LiveData<ScreenState<List<Employee>?>>
        get() = _employeesLiveData

    init {
        fetchEmployees()
    }

    fun fetchEmployees() {
        // Set to loading state to show progress bar
        _employeesLiveData.postValue(ScreenState.Loading(null))

        // Pass in Dispatchers.IO to run on background thread
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val client = repository.getEmployees()
                val employees = client.employees

                if (employees.isEmpty()) {
                    _employeesLiveData.postValue(ScreenState.Empty("No employees found"))
                } else {
                    // Sort alphabetically by employee name
                    val sortedEmployees = employees.sortedBy { it.fullName }
                    _employeesLiveData.postValue(ScreenState.Success(sortedEmployees))
                }
            }
            catch(e: JsonDataException) {
                _employeesLiveData.postValue(ScreenState.Error(
                    "JSON data malformed! Unable to fetch employees.",
                    null
                ))
            }
            catch(e: Exception) {
                _employeesLiveData.postValue(ScreenState.Error(e.message.toString(), null))
            }
        }
    }
}