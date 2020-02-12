package com.nike.coding.dictionary.network

import com.nike.coding.dictionary.network.model.Definition
import io.reactivex.Single

class DictionaryDefinitionsProvider(private val rapidApiService: RapidApiService, private val schedulerHelper: SchedulerHelper) {

    fun getDefinitions(queryString: String) : Single<List<Definition>> {
        return rapidApiService.getDefinitions(queryString)
                .flatMap { definitions -> Single.just(definitions.list) }
                .compose(schedulerHelper.applySingleSchedulers())
    }
}