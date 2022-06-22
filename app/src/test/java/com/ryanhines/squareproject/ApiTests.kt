package com.ryanhines.squareproject

import com.ryanhines.squareproject.network.ApiClient
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

class ApiTests {

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Check to see if API response isn't empty`() {
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
            )
            val response = ApiClient.apiService.fetchEmployees()
            assert(response.employees.isNotEmpty())
        }
    }

    @Test
    fun `Check to see if API response is empty`() {
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
            )
            val response = ApiClient.apiService.fetchEmployeesEmpty()
            assert(response.employees.isEmpty())
        }
    }
}