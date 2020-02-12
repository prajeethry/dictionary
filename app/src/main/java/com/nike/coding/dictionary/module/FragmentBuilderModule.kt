package com.nike.coding.dictionary.module

import com.nike.coding.dictionary.views.fragment.DictionaryDefinitionsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeDictionaryDefinitionsListFragment(): DictionaryDefinitionsListFragment
}