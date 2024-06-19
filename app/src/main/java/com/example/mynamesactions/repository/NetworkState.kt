package com.example.mynamesactions.repository

import java.lang.Exception

sealed class NetworkState<out T> {
    data class Error(val exception: Exception) : NetworkState<Nothing>()
    data class Success<T>(val data: T) : NetworkState<T>()
}