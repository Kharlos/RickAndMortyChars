package com.cblanco.rickandmortychars.features.characters.list.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.cblanco.rickandmortychars.core.extensions.async
import com.cblanco.rickandmortychars.core.presentation.viewmodel.BaseViewModel
import com.cblanco.rickandmortychars.core.presentation.viewmodel.EventDelegate
import com.cblanco.rickandmortychars.core.presentation.viewmodel.EventDelegateViewModel
import com.cblanco.rickandmortychars.features.characters.list.domain.model.CharacterItemModel
import com.cblanco.rickandmortychars.features.characters.list.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel
@Inject constructor(
    private val characterListRepository: CharacterRepository
) : BaseViewModel<CharcterListState, CharacterListIntent>(),
    EventDelegate<CharacterListEvent> by EventDelegateViewModel() {

    override var viewState by mutableStateOf(CharcterListState())

    init {
        onFetchData()
    }

    override fun processIntent(intent: CharacterListIntent) = when (intent) {
        is CharacterListIntent.FetchData -> onFetchData()
        is CharacterListIntent.ClickCharacter -> onClickCharacter(intent.character)
    }


    private fun onFetchData() {
        viewState = viewState.copy(loading = true)

        async(characterListRepository.getCharacterList(), {
            viewState = viewState.copy(
                loading = false, characterList = it
            )
        }, { error ->
            Log.e("catest", "error $error")
            viewState = viewState.copy(
                loading = false
            )
            viewModelScope.launch {
                if (error is String) {
                    sendEvent(CharacterListEvent.Error(error))
                }else if(error is Int){
                    sendEvent(CharacterListEvent.ErrorKey(error))
                }

            }
        })
    }

    private fun onClickCharacter(character: CharacterItemModel) {
        viewModelScope.launch {
            sendEvent(CharacterListEvent.NavigateToDetail(character))
        }
    }


}