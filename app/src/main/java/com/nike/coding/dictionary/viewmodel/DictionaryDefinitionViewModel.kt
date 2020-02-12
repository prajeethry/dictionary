package com.nike.coding.dictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nike.coding.dictionary.network.DictionaryDefinitionsProvider
import com.nike.coding.dictionary.network.Resource
import com.nike.coding.dictionary.network.model.Definition
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DictionaryDefinitionViewModel @Inject constructor(private val dictionaryDefinitionsProvider: DictionaryDefinitionsProvider) : ViewModel() {
    private val definitions: MutableLiveData<Resource<List<Definition>?>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable?

    init {
        compositeDisposable = CompositeDisposable()
    }

    fun getDefinition(query: String) {
        definitions.setValue(Resource.loading(null))
        compositeDisposable?.add(dictionaryDefinitionsProvider.getDefinitions(query).subscribe(this::onSuccess, this::onError))
    }

    fun getDefinitions(): LiveData<Resource<List<Definition>?>> = definitions


    private fun onSuccess(repositories: List<Definition>) {
        definitions.value = Resource.success(repositories)
    }

    private fun onError(t: Throwable) {
        definitions.value = Resource.error(t)
    }

    override fun onCleared() {
        compositeDisposable?.clear()
        compositeDisposable = null
    }
}