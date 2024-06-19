package com.example.mynamesactions.repository

import com.example.mynamesactions.api.NamesApi
import com.example.mynamesactions.api.NamesDto
import com.example.mynamesactions.data.Names
import java.lang.Exception

class NamesRepositoryImpl(private val namesApi: NamesApi) : NamesRepository {

    override suspend fun getNames(): NetworkState<List<Names>> {
        return try {
            val names = transformList(namesApi.getNames())

            NetworkState.Success(names)
        } catch (exception: Exception) {
            NetworkState.Error(exception)
        }
    }

    fun transformList(input: List<NamesDto>): List<Names> {
        val groupedByListId = input.groupBy { it.listId }

        val sortedGroupedList = groupedByListId.filterKeys { it != null }.flatMap { (_, list) ->
            list.filter { !it.name.isNullOrBlank() }
                .map { Names(it.id, it.listId, it.name) }
                .sortedWith(compareBy({ it.name }, { it.id }))
        }

        return sortedGroupedList
    }
}
