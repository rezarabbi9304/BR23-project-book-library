package com.example.booklibrarybrain.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrarybrain.domain.useCase.getBookCase
import com.example.booklibrarybrain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
  private val  useCase:getBookCase
):ViewModel() {


    private val _state = mutableStateOf(BookState())
    val state = _state

    init {
        getBooks()
    }

    fun getBooks(){
            viewModelScope.launch {
                useCase.invoke().onEach {resource ->
                    when(resource){
                        is Resource.Error -> {
                            _state.value = resource.data?.let {
                                state.value.copy(
                                    message = resource.message,
                                    bookItem = it
                                )
                            }!!
                        }
                        is Resource.Loading -> {
                            _state.value = resource.data?.let {
                                state.value.copy(
                                    bookItem = it,
                                    message = "Loading Data....."
                                )
                            }!!
                        }
                        is Resource.Success -> {
                            _state.value = resource?.data?.let {
                                state.value.copy(
                                    bookItem = it,
                                    message = resource.message
                                )
                            }!!
                        }
                    }

                }.launchIn(this)
            }
    }

}