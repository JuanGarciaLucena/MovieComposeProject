package com.emebesoft.movieProject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emebesoft.movieProject.data.repository.CharacterRepositoryImpl
import com.emebesoft.movieProject.ui.common.UiState
import com.emebesoft.movieProject.ui.common.asResult
import com.emebesoft.movieProject.ui.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterRepositoryImpl: CharacterRepositoryImpl): ViewModel() {

    private val _charactersFlow = MutableStateFlow<UiState>(UiState.Loading)
    val characterFlow: StateFlow<UiState> = _charactersFlow.asStateFlow()

    fun getAllCharacters(){
        viewModelScope.launch {
            characterRepositoryImpl.getCharacters().asResult().collect{ result ->
                _charactersFlow.update {
                    when(result) {
                        is Result.Loading -> UiState.Loading
                        is Result.Success -> UiState.Success(result.data)
                        is Result.Error -> UiState.Error(result.exception)
                    }
                }
            }
        }
    }
}