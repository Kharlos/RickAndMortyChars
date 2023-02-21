package com.cblanco.rickandmortychars.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

abstract class BaseViewModel<State,Intent> : ViewModel() {

    private val _intents = Channel<Intent>(Channel.BUFFERED)

    abstract var viewState:State
        protected set

    init {
        viewModelScope.launch {
            _intents.consumeEach {
                processIntent(it)
            }
        }
    }

    abstract fun processIntent(intent:Intent)

    open fun sendIntent(intent: Intent) {
        viewModelScope.launch {
            _intents.send(intent)
        }
    }

}