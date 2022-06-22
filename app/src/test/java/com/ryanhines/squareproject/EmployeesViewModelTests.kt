package com.ryanhines.squareproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ryanhines.squareproject.util.ScreenState
import com.ryanhines.squareproject.viewmodels.EmployeesViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmployeesViewModelTests {

    private lateinit var vm: EmployeesViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        vm = EmployeesViewModel()
    }

    @Test
    fun `Test if fetch employees returns loading and successful states`() {
        vm.fetchEmployees()
        val states = vm.employeeLiveData.getOrAwaitValuesTest(maxCountDown = 2)

        assert(
            states.any { it is ScreenState.Loading } &&
            states.any { it is ScreenState.Success }
        )
    }

    @Test
    fun `Test if success state employees list is not empty`() {
        vm.fetchEmployees()
        val states = vm.employeeLiveData.getOrAwaitValuesTest(maxCountDown = 2)
        val success = states.find { it is ScreenState.Success }

        success?.data?.isNotEmpty()?.let { assert(it) }
    }
}