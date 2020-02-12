package com.nike.coding.dictionary.module

import com.nike.coding.dictionary.views.activity.DictionaryDefinitionsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun dictionaryDefinitionsListActivity(): DictionaryDefinitionsListActivity
}