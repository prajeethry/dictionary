package com.nike.coding.dictionary.views

import com.nike.coding.dictionary.network.model.Definition

interface RepositoryListCallback {
    fun onRepositoryClicked(definition: Definition)
}