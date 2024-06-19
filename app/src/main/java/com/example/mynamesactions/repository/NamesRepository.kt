package com.example.mynamesactions.repository

import com.example.mynamesactions.data.Names

interface NamesRepository {

    suspend fun getNames(): NetworkState<List<Names>>
}