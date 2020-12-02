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
class GetTeamDetailsUseCaseTest : BaseTest() {

    private lateinit var getTeamDetailsUseCase: GetTeamDetailsUseCase

    private val repository: Repository = mock()
    private val errorHandler: ErrorHandler = mock()

    @Before
    fun setUp() {
        getTeamDetailsUseCase =
            GetTeamDetailsUseCase(errorHandler, dispatcherProvider, repository)
    }

    @Test
    fun `get team details success`() {
        testCoroutineRule.runBlockingTest {
            val id = 1

            whenever(repository.getTeamDetails(id)).thenReturn(Unit)

            val result = getTeamDetailsUseCase.invoke(GetTeamDetailsUseCase.Params(id))

            Assert.assertFalse(result is DataResult.Failure)
        }
    }

    @Test
    fun `get team details failure`() {
        testCoroutineRule.runBlockingTest {

            val exception = NullPointerException()
            val message = "Error"
            val id = 1

            whenever(errorHandler.handleError(exception)).thenReturn(
                DataResult.Failure(
                    DataException.UnknownException(message)
                )
            )
            whenever(repository.getTeamDetails(id)).thenThrow(exception)

            val result = getTeamDetailsUseCase.invoke(GetTeamDetailsUseCase.Params(id))

            Assert.assertTrue(result is DataResult.Failure)
            val failure = result as DataResult.Failure
            Assert.assertEquals(message, failure.exception.message)
        }
    }
}