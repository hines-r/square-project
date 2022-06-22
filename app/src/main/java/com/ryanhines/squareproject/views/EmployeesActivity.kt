package com.ryanhines.squareproject.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ryanhines.squareproject.R
import com.ryanhines.squareproject.views.fragments.EmployeesFragment

class EmployeesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees)

        // Set the FrameLayout to display the employees fragment
        val employeesFragment = EmployeesFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, employeesFragment)
            commit()
        }
    }
}