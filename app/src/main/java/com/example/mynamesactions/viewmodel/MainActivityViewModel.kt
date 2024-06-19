package com.example.mynamesactions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynamesactions.data.ListItem
import com.example.mynamesactions.data.Names
import com.example.mynamesactions.repository.NetworkState
import com.example.mynamesactions.repository.NamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: NamesRepository
) : ViewModel() {
    private val _mapData: MutableLiveData<List<ListItem>> = MutableLiveData()
    val mapData: LiveData<List<ListItem>> get() = _mapData

    private val _selectedNames: MutableLiveData<List<Names>> = MutableLiveData()
    val selectedNames: LiveData<List<Names>> get() = _selectedNames

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError:  MutableLiveData<Boolean> = MutableLiveData()
    val isError: LiveData<Boolean> get() = _isError

    private var job: Job? = null

    fun fetchNames() {
        _isLoading.postValue(true)
        job = viewModelScope.launch {
            when (val response = repository.getNames()) {

                is NetworkState.Success -> {
                    val map = transformList(response.data)
                    if (response.data.isNotEmpty()) {
                        withContext(Dispatchers.Main) {
                            _isLoading.postValue(false)
                            _mapData.postValue(map)
                        }
                    }
                }

                is NetworkState.Error -> {
                    onError()
                }
            }
        }
    }

    fun selectNewsArticle(id: Int) {
        _selectedNames.value = _mapData.value?.get(id)?.names
    }

    private fun transformList(input: List<Names>): List<ListItem> {

        val filteredList = input.filter { !it.name.isNullOrBlank() }

        val namesList = filteredList.map { Names(it.id, it.listId, it.name!!) }

        val groupedByListId = namesList.groupBy { it.listId }

        val listItems = groupedByListId.map { (listId, names) ->
            ListItem(
                listId = listId,
                names = names.sortedWith(compareBy({ it.name }, { it.id }))
            )
        }.sortedBy { it.listId }

        return listItems
    }

    private fun onError() {
        _isLoading.postValue(false)
        _isError.postValue(true)
    }
}