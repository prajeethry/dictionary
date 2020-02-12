package com.nike.coding.dictionary.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nike.coding.dictionary.network.DictionaryDefinitionsProvider
import com.nike.coding.dictionary.network.Status
import com.nike.coding.dictionary.network.model.Definition
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DictionaryDefinitionViewModelTest {

    @Rule
    @JvmField
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: DictionaryDefinitionViewModel

    @MockK
    private lateinit var dictionaryDefinitionsProvider: DictionaryDefinitionsProvider

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        subject = DictionaryDefinitionViewModel(dictionaryDefinitionsProvider)
    }

    @Test
    fun `returns error when get definitions throws error`() {
        val throwable = mockk<Throwable>()
        every { dictionaryDefinitionsProvider.getDefinitions("wat") } returns Single.error(throwable)

        subject.getDefinition("wat")

        assertEquals(subject.getDefinitions().value?.status, Status.ERROR)
    }

    @Test
    fun `returns list of definitions when get definitions is success`() {
        val definition = mockk<Definition>()

        every { dictionaryDefinitionsProvider.getDefinitions("wat") } returns Single.just(
            arrayListOf(definition, definition))

        subject.getDefinition("wat")

        assertEquals(subject.getDefinitions().value?.status, Status.SUCCESS)
        assertEquals(subject.getDefinitions().value?.data?.size, 2)
    }
}