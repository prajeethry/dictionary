package com.nike.coding.dictionary.network

import com.nike.coding.dictionary.network.model.Definitions
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RapidApiService {

    @GET("define")
    fun getDefinitions(@Query("term") term: String): Single<Definitions>
}