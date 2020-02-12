package com.nike.coding.dictionary.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nike.coding.dictionary.annotations.ViewModelKey
import com.nike.coding.dictionary.viewmodel.DictionaryDefinitionViewModel
import com.nike.coding.dictionary.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DictionaryDefinitionViewModel::class)
    abstract fun bindDictionaryDefinitionViewModel(dictionaryDefinitionViewModel: DictionaryDefinitionViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}