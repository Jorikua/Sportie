package com.example.domain.usecase

import com.example.base.data.DataResult
import com.example.base.network.DataException
import com.example.base.network.ErrorHandler
import com.example.data.Repository
import com.example.test.BaseTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetTeamsUseCaseTest : BaseTest() {

    private lateinit var getTeamsUseCase: GetTeamsUseCase

    private val repository: Repository = mock()
    private val errorHandler: ErrorHandler = mock()

    @Before
    fun setUp() {
        getTeamsUseCase =
            GetTeamsUseCase(errorHandler, dispatcherProvider, repository)
    }

    @Test
    fun `get teams success`() {
        testCoroutineRule.runBlockingTest {
            whenever(repository.getTeams()).thenReturn(Unit)

            val result = getTeamsUseCase.invoke(Unit)

            Assert.assertFalse(result is DataResult.Failure)
        }
    }

    @Test
    fun `get teams failure`() {
        testCoroutineRule.runBlockingTest {

            val exception = NullPointerException()
            val message = "Error"

            whenever(errorHandler.handleError(exception)).thenReturn(
                DataResult.Failure(
                    DataException.UnknownException(message)
                )
            )
            whenever(repository.getTeams()).thenThrow(exception)

            val result = getTeamsUseCase.invoke(Unit)

            Assert.assertTrue(result is DataResult.Failure)
            val failure = result as DataResult.Failure
            Assert.assertEquals(message, failure.exception.message)
        }
    }
}