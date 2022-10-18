package com.sdk.bigtextshower.viewmodel

import androidx.annotation.FontRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val _state: MutableStateFlow<State> = MutableStateFlow(State())
    val state: StateFlow<State> get() = _state

    @OptIn(DelicateCoroutinesApi::class)
    private val singleThread = newSingleThreadContext("ViewModel Single Thread")

    private suspend fun reduce(state: (State) -> State) = withContext(singleThread) {
        val lastState = _state.value
        _state.emit(state(lastState))
    }

    fun setText(text: String) {
        viewModelScope.launch {
            reduce {
                it.copy(
                    text = text
                )
            }
        }
    }

    fun setTextSize(textSize: Int) {
        viewModelScope.launch {
            reduce {
                it.copy(
                    textSize = textSize
                )
            }
        }
    }

    fun setTextColor(color: Int) {
        viewModelScope.launch {
            reduce {
                it.copy(
                    textColor = color
                )
            }
        }
    }

    fun setTextSpeed(speed: Int) {
        viewModelScope.launch {
            reduce {
                it.copy(
                    textSpeed = speed
                )
            }
        }
    }

    fun setBackgroundColor(color: Int) {
        viewModelScope.launch {
            reduce {
                it.copy(
                    backgroundColor = color
                )
            }
        }
    }

    fun setFont(@FontRes font: Int) {
        viewModelScope.launch {
            reduce {
                it.copy(
                    font = font
                )
            }
        }
    }

    fun setLedType(type: String) {
        viewModelScope.launch {
            reduce {
                it.copy(
                    ledType = type
                )
            }
        }
    }
}
