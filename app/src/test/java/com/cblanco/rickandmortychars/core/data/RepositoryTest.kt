package com.cblanco.rickandmortychars.core.data

import io.mockk.MockKAnnotations
import org.junit.Before

open class RepositoryTest {

    val mockWebServer = MockWebServer()

    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
    }
}