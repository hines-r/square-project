package com.ryanhines.squareproject.models

import com.squareup.moshi.Json

data class EmployeeResponse(
    @Json(name="employees")
    val employees : List<Employee>
)

