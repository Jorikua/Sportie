package com.example.domain.usecase

import com.example.base.data.DataResult
import com.example.base.network.DataException
import com.example.base.network.ErrorHandler
import com.example.data.Repository
import com.example.data.db.model.TeamModel
import com.example.test.BaseTest
import com.example.test.asFlow
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ObserveTeamsUseCaseTest : BaseTest() {

    private lateinit var observeTeamsUseCase: ObserveTeamsUseCase

    private val repository: Repository = mock()
    private val errorHandler: ErrorHandler = mock()

    private val teamsResult = listOf(TeamModel(1, "aa", true, "male", "desc", "url"))

    @Before
    fun setUp() {
        observeTeamsUseCase =
            ObserveTeamsUseCase(errorHandler, dispatcherProvider, repository)
    }

    @Test
    fun `observe team details success`() {
        testCoroutineRule.runBlockingTest {

            whenever(repository.observeTeams()).thenReturn(teamsResult.asFlow())

            val result = observeTeamsUseCase.invoke(Unit).first()

            Assert.assertFalse(result is DataResult.Failure)
            val teams = (result as DataResult.Success).data

            Assert.assertTrue(teams.isNotEmpty())
            Assert.assertEquals(teams[0].id, teamsResult[0].id)
        }
    }

    @Test
    fun `observe team details failure`() {
        testCoroutineRule.runBlockingTest {

            val message = "Error"

            whenever(errorHandler.handleError(any())).thenReturn(
                DataResult.Failure(
                    DataException.UnknownException(message)
                )
            )

            whenever(repository.observeTeams()).doReturn(flow { throw NullPointerException() })

            val result = observeTeamsUseCase.invoke(Unit).first()
            Assert.assertTrue(result is DataResult.Failure)
            val failure = result as DataResult.Failure
            Assert.assertEquals(message, failure.exception.message)

        }
    }
}