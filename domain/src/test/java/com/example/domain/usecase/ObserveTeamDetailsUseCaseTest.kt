package com.example.domain.usecase

import com.example.base.data.DataResult
import com.example.base.network.DataException
import com.example.base.network.ErrorHandler
import com.example.data.Repository
import com.example.data.db.model.TeamModel
import com.example.test.BaseTest
import com.example.test.asFlow
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ObserveTeamDetailsUseCaseTest : BaseTest() {

    private lateinit var observeTeamDetailsUseCase: ObserveTeamDetailsUseCase

    private val repository: Repository = mock()
    private val errorHandler: ErrorHandler = mock()

    private val teamModel = TeamModel(1, "aa", true, "male", "desc", "url")

    @Before
    fun setUp() {
        observeTeamDetailsUseCase =
            ObserveTeamDetailsUseCase(errorHandler, dispatcherProvider, repository)
    }

    @Test
    fun `observe team details success`() {
        testCoroutineRule.runBlockingTest {
            val id = teamModel.id

            whenever(repository.observeTeamDetails(id)).thenReturn(teamModel.asFlow())

            val result =
                observeTeamDetailsUseCase.invoke(ObserveTeamDetailsUseCase.Params(teamModel.id))
                    .first()

            Assert.assertFalse(result is DataResult.Failure)
            val team = (result as DataResult.Success).data

            Assert.assertNotNull(team)
            Assert.assertEquals(team.id, teamModel.id)
        }
    }

    @Test
    fun `observe team details failure`() {
        testCoroutineRule.runBlockingTest {

            val exception = NullPointerException()
            val message = "Error"

            whenever(errorHandler.handleError(exception)).thenReturn(
                DataResult.Failure(
                    DataException.UnknownException(message)
                )
            )
            repository.stub {
                onBlocking { repository.observeTeamDetails(teamModel.id) } doThrow exception
            }
//            whenever(repository.observeTeamDetails(teamModel.id)).thenThrow(exception)

            val result =
                observeTeamDetailsUseCase.invoke(ObserveTeamDetailsUseCase.Params(teamModel.id))
                    .single()
            Assert.assertTrue(result is DataResult.Failure)
            val failure = result as DataResult.Failure
            Assert.assertEquals(message, failure.exception.message)

        }
    }
}