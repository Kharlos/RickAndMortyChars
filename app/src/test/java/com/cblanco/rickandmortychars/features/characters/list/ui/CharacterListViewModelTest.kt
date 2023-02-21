package com.cblanco.rickandmortychars.features.characters.list.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cblanco.rickandmortychars.R
import com.cblanco.rickandmortychars.core.domain.RequestFailure
import com.cblanco.rickandmortychars.core.domain.ResultOf
import com.cblanco.rickandmortychars.features.characters.list.domain.model.CharacterItemModel
import com.cblanco.rickandmortychars.features.characters.list.domain.repository.CharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharacterListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    //Subject of test
    private lateinit var viewModel: CharacterListViewModel

    @MockK
    private lateinit var characterRepository: CharacterRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CharacterListViewModel(characterRepository)
    }

    @Test
    fun `on fetch data state has correct data`() = runTest  {

        coEvery {
            characterRepository.getCharacterList()
        } returns flow { emit(ResultOf.Success(listOf(CharacterItemModel(1, "","","","","","","","")))) }

        viewModel.sendIntent(CharacterListIntent.FetchData)

        Assert.assertEquals(1, viewModel.viewState.characterList.size)
        Assert.assertTrue(viewModel.viewState.loading.not())
    }

    @Test
    fun `on fetch data with error return text id error`() = runTest {

        coEvery { characterRepository.getCharacterList() } returns flow {
            emit(
                ResultOf.Failure(
                    RequestFailure.UnknownError
                )
            )
        }

        viewModel.sendIntent(CharacterListIntent.FetchData)

        val event = viewModel.viewEvents.first() as CharacterListEvent.ErrorKey

        Assert.assertEquals(0, viewModel.viewState.characterList.size)
        assertEquals(R.string.default_error_message, event.idReference)
    }


    private fun setRepoReturnValidData() {
    }
}