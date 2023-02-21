package com.cblanco.rickandmortychars.features.characters.list.data

import com.cblanco.rickandmortychars.core.data.RepositoryTest
import com.cblanco.rickandmortychars.core.data.api.APIService
import com.cblanco.rickandmortychars.core.domain.RequestFailure
import com.cblanco.rickandmortychars.core.domain.ResultOf
import com.cblanco.rickandmortychars.features.characters.list.domain.model.CharacterItemModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterListDataRepositoryTest: RepositoryTest() {

    private lateinit var sut: CharacterDataRepository

    @MockK
    private lateinit var apiService: APIService

    @Before
    override fun setUp() {
        super.setUp()
        sut = CharacterDataRepository(apiService)
    }

    @Test
    fun `on successful request object return flow with object`() = runBlocking {
        coEvery { apiService.getCharacters() } returns mockWebServer.responseCharacterList()

        sut.getCharacterList().collect {
            assertServiceDataContainsExpectedValues((it as ResultOf.Success<List<CharacterItemModel>>).value)
        }
    }

    @Test
    fun `on api error request object return flow with api error`() = runBlocking {
        coEvery { apiService.getCharacters() } returns mockWebServer.responseWithApiError()

        sut.getCharacterList().collect {
            val failure = ((it as ResultOf.Failure).requestFailure as RequestFailure.ApiError)
            assertEquals("Error.", failure.message)
        }
    }

    private fun assertServiceDataContainsExpectedValues(testServiceData: List<CharacterItemModel>) {
        assertEquals(20, testServiceData.size)
    }
}
