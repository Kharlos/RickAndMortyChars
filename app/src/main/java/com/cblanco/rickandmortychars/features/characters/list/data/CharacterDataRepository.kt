package com.cblanco.rickandmortychars.features.characters.list.data

import com.cblanco.rickandmortychars.core.data.api.APIService
import com.cblanco.rickandmortychars.core.data.api.FailureFactory
import com.cblanco.rickandmortychars.core.domain.RequestFailure
import com.cblanco.rickandmortychars.core.domain.ResultOf
import com.cblanco.rickandmortychars.core.extensions.safeCall
import com.cblanco.rickandmortychars.features.characters.list.data.response.CharacterListApiResponse
import com.cblanco.rickandmortychars.features.characters.list.domain.model.CharacterItemModel
import com.cblanco.rickandmortychars.features.characters.list.domain.model.toDomain
import com.cblanco.rickandmortychars.features.characters.list.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CharacterDataRepository(private val apiService: APIService) : CharacterRepository {


    override fun getCharacterList(): Flow<ResultOf<List<CharacterItemModel>>>  = flow {
        emit(apiService.getCharacters().safeCall({ response -> response.results.map { it.toDomain() }}))
    }.catch {
        emit(FailureFactory().handleException(it))
    }
}