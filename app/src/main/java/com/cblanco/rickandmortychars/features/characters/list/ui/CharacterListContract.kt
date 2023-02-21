package com.cblanco.rickandmortychars.features.characters.list.ui

import com.cblanco.rickandmortychars.features.characters.list.data.response.CharacterListInfoApiResponse
import com.cblanco.rickandmortychars.features.characters.list.data.response.CharacterListItemApiResponse
import com.cblanco.rickandmortychars.features.characters.list.domain.model.CharacterItemModel

data class CharcterListState (
    val loading: Boolean = false,
    val characterList: List<CharacterItemModel> = listOf()
)

sealed class CharacterListIntent {
    object FetchData : CharacterListIntent()
    data class ClickCharacter(val character: CharacterItemModel): CharacterListIntent()
}

sealed class CharacterListEvent{
    data class ErrorKey(val idReference: Int) : CharacterListEvent()
    data class Error(val message: String) : CharacterListEvent()
    data class NavigateToDetail(val character: CharacterItemModel) : CharacterListEvent()
}