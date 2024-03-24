package com.emebesoft.movieProject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emebesoft.movieProject.data.repository.CharacterRepositoryImpl
import com.emebesoft.movieProject.ui.states.DetailUiState
import com.emebesoft.movieProject.ui.states.HomeUiState
import com.emebesoft.movieProject.ui.common.asResult
import com.emebesoft.movieProject.ui.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickMortyCharacterViewModel @Inject constructor(
    private val characterRepositoryImpl: CharacterRepositoryImpl
): ViewModel() {

    private val _charactersFlow = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val characterFlow: StateFlow<HomeUiState> = _charactersFlow.asStateFlow()

    private val _detailFlow = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailFlow: StateFlow<DetailUiState> = _detailFlow

    init {
        getAllCharacters()
    }

    private fun getAllCharacters(){
        viewModelScope.launch {
            characterRepositoryImpl.getCharacters().asResult().collect{ result ->
                _charactersFlow.update {
                    when(result) {
                        is Result.Loading -> {
                            Log.i("HOME_STATE", "LOADING")
                            HomeUiState.Loading
                        }
                        is Result.Success -> {
                            Log.i("HOME_STATE", "SUCCESS")
                            HomeUiState.Success(result.data)
                        }
                        is Result.Error -> {
                            Log.i("HOME_STATE", "ERROR")
                            HomeUiState.Error(result.exception)
                        }
                    }
                }
            }
        }
    }

    fun getCharacter(characterId: String){
        viewModelScope.launch {
            characterRepositoryImpl.getCharacter(characterId).asResult().collect { result ->
                _detailFlow.update {
                    when(result) {
                        is Result.Loading -> {
                            Log.i("DETAIL_STATE", "LOADING")
                            DetailUiState.Loading
                        }
                        is Result.Success -> {
                            Log.i("DETAIL_STATE", "SUCCESS")
                            DetailUiState.Success(result.data)
                        }
                        is Result.Error -> {
                            Log.i("DETAIL_STATE", "ERROR")
                            DetailUiState.Error(result.exception)
                        }
                    }
                }
            }
        }
    }
}