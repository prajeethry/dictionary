package com.nike.coding.dictionary.module

import android.app.Application
import android.content.Context
import com.nike.coding.dictionary.module.ViewModelModule
import com.nike.coding.dictionary.network.module.DictionaryNetworkModule
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class, DictionaryNetworkModule::class])
class ApplicationModule {

    @Provides
    fun providesApplication(application: Application): Context {
        return application
    }
}