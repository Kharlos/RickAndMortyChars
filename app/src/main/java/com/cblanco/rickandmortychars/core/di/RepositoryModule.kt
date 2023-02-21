package com.cblanco.rickandmortychars.core.di

import com.cblanco.rickandmortychars.core.data.api.APIService
import com.cblanco.rickandmortychars.features.characters.list.data.CharacterDataRepository
import com.cblanco.rickandmortychars.features.characters.list.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(api: APIService): CharacterRepository {
        return CharacterDataRepository(api)
    }

}