package com.cblanco.rickandmortychars.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cblanco.rickandmortychars.core.domain.ResultOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <S>ViewModel.async(flow: Flow<ResultOf<S>>, crossinline success: (S) -> Unit, crossinline error: (Any) -> Unit) =
    viewModelScope.launch {
        flow.collect {
            when (it){
                is ResultOf.Success -> success(it.value)
                is ResultOf.Failure -> error(it.requestFailure.errorManager())
            }
        }
    }