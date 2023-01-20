package com.addressBook

import org.junit.jupiter.api.BeforeAll

open class AppTest {
    companion object {
        lateinit var appCtx: AppContext

        @JvmStatic
        @BeforeAll
        fun setUp() {
            println("---------------")
            val db = connectToDatabase()
            appCtx = AppContext(db)

            resetDatabase()
        }
    }
}