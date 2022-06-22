package com.ryanhines.squareproject.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.ryanhines.squareproject.R
import com.ryanhines.squareproject.adapters.EmployeeAdapter
import com.ryanhines.squareproject.models.Employee
import com.ryanhines.squareproject.network.ApiClient
import com.ryanhines.squareproject.repository.EmployeeRepository
import com.ryanhines.squareproject.util.ScreenState
import com.ryanhines.squareproject.viewmodels.EmployeesViewModel
import com.ryanhines.squareproject.viewmodels.EmployeesViewModelFactory

class EmployeesFragment : Fragment(R.layout.fragment_employees) {

    private lateinit var viewModel: EmployeesViewModel

    private lateinit var progressBar: ProgressBar
    private lateinit var emptyText: TextView
    private lateinit var errorText: TextView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repo =  EmployeeRepository(ApiClient.apiService)
        val viewModelFactory = EmployeesViewModelFactory(repo)
        viewModel = ViewModelProvider(this, viewModelFactory)[EmployeesViewModel::class.java]

        progressBar = view.findViewById(R.id.pbLoading)
        emptyText = view.findViewById(R.id.tvEmpty)
        errorText = view.findViewById(R.id.tvError)
        swipeRefresh = view.findViewById(R.id.srRefreshEmployees)

        viewModel.employeeLiveData.observe(viewLifecycleOwner) { state ->
            processEmployeesResponse(state)
        }

        swipeRefresh.isEnabled = false // Disable until loading finishes
        swipeRefresh.setOnRefreshListener {
            viewModel.fetchEmployees() // Refresh the employees recycler view
            swipeRefresh.isRefreshing = false
        }
    }

    /**
     * Manages the view based upon the possible screen states
     */
    private fun processEmployeesResponse(state: ScreenState<List<Employee>?>) {
        when (state) {
            is ScreenState.Loading -> {
                handleVisibilities(state)
                swipeRefresh.isEnabled = false
            }
            is ScreenState.Success -> {
                handleVisibilities(state)
                if (state.data != null) {
                    val employeeAdapter = EmployeeAdapter(requireActivity(), state.data)
                    val recyclerView = view?.findViewById<RecyclerView>(R.id.rvEmployees)
                    recyclerView?.layoutManager = LinearLayoutManager(activity)
                    recyclerView?.adapter = employeeAdapter
                }
                swipeRefresh.isEnabled = true
            }
            is ScreenState.Empty -> {
                handleVisibilities(state)
                swipeRefresh.isEnabled = true
            }
            is ScreenState.Error -> {
                handleVisibilities(state)
                val view = progressBar.rootView
                Snackbar.make(view, state.message!!, Snackbar.LENGTH_LONG).show()
                swipeRefresh.isEnabled = true
            }
        }
    }

    /**
     * Manages the visibilities of user interface elements based upon
     * the current screen state
     */
    private fun handleVisibilities(state: ScreenState<List<Employee>?>) {
        when (state) {
            is ScreenState.Loading -> {
                progressBar.visibility = View.VISIBLE
                emptyText.visibility = View.GONE
                errorText.visibility = View.GONE
            }
            is ScreenState.Success -> {
                progressBar.visibility = View.GONE
                emptyText.visibility = View.GONE
                errorText.visibility = View.GONE
            }
            is ScreenState.Empty -> {
                progressBar.visibility = View.GONE
                emptyText.visibility = View.VISIBLE
                errorText.visibility = View.GONE
            }
            is ScreenState.Error -> {
                progressBar.visibility = View.GONE
                emptyText.visibility = View.GONE
                errorText.visibility = View.VISIBLE
            }
        }
    }
}