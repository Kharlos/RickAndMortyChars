package com.cblanco.rickandmortychars.features.characters.list.domain.repository

import com.cblanco.rickandmortychars.core.domain.ResultOf
import com.cblanco.rickandmortychars.features.characters.list.data.response.CharacterListApiResponse
import com.cblanco.rickandmortychars.features.characters.list.domain.model.CharacterItemModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacterList(): Flow<ResultOf<List<CharacterItemModel>>>

}