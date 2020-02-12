package com.nike.coding.dictionary

import com.nike.coding.dictionary.module.ActivityModule
import com.nike.coding.dictionary.module.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityModule::class, ApplicationModule::class])
interface DictionaryAppComponent : AndroidInjector<DictionaryApplication>
