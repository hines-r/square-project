package com.ryanhines.squareproject.models

import com.squareup.moshi.Json
import java.io.Serializable

data class Employee (
        @Json(name="uuid")
        val id: String,

        @Json(name="full_name")
        val fullName: String,

        @Json(name="phone_number")
        val phoneNumber: String?,

        @Json(name="email_address")
        val email: String,

        @Json(name="biography")
        val biography: String?,

        @Json(name="photo_url_small")
        val smallPhotoURL: String?,

        @Json(name="photo_url_large")
        val largePhotoURL: String?,

        @Json(name="team")
        val team: String,

        @Json(name="employee_type")
        val employeeType: EmployeeType,
) : Serializable

enum class EmployeeType(val value: String) {
        FULL_TIME("Full Time"),
        PART_TIME("Part Time"),
        CONTRACTOR("Contractor")
}

