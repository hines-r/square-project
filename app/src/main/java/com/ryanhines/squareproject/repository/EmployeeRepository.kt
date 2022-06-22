package com.ryanhines.squareproject.repository

import com.ryanhines.squareproject.network.ApiService

class EmployeeRepository(
    private val apiService: ApiService
) {
    suspend fun getEmployees() = apiService.fetchEmployees()
    suspend fun getEmployeesMalformed() = apiService.fetchEmployeesMalformed()
    suspend fun getEmployeesEmpty() = apiService.fetchEmployeesEmpty()
}