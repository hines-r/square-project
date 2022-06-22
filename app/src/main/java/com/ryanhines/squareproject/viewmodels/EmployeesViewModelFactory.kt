package com.ryanhines.squareproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ryanhines.squareproject.repository.EmployeeRepository

/**
 * Used to pass in parameters to our EmployeeViewModel
 */
class EmployeesViewModelFactory(
    private val employeesRepository: EmployeeRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        EmployeesViewModel(employeesRepository) as T
}