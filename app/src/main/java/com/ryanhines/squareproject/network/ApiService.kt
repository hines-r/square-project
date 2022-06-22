package com.ryanhines.squareproject.network

import com.ryanhines.squareproject.models.EmployeeResponse
import retrofit2.http.GET

interface ApiService {
    @GET("employees.json")
    suspend fun fetchEmployees(): EmployeeResponse

    @GET("employees_malformed.json")
    suspend fun fetchEmployeesMalformed(): EmployeeResponse

    @GET("employees_empty.json")
    suspend fun fetchEmployeesEmpty(): EmployeeResponse
}