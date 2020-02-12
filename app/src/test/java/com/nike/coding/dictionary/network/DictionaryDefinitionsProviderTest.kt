package com.nike.coding.dictionary.network

import com.nike.coding.dictionary.network.model.Definitions
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.SingleTransformer
import org.junit.Before
import org.junit.Test

class DictionaryDefinitionsProviderTest {

    @MockK
    private lateinit var schedulerHelper: SchedulerHelper

    @MockK
    private lateinit var rapidApiService: RapidApiService

    private lateinit var subject: DictionaryDefinitionsProvider

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        every { schedulerHelper.applySingleSchedulers<Any>() } returns (SingleTransformer { single -> single })

        subject = DictionaryDefinitionsProvider(rapidApiService, schedulerHelper)
    }

    @Test
    fun `returns definitions list when rapid api service is success`() {
        val definitions = mockk<Definitions>()
        every { definitions.list } returns arrayListOf()
        every { rapidApiService.getDefinitions("wat") } returns Single.just(definitions)

        val testObserver = subject.getDefinitions("wat").test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
    }
}