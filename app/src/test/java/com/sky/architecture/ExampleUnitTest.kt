package com.sky.architecture

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @org.junit.Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @org.junit.Test
    fun testConstructor() {
        val test = Test("tony")
        System.out.println(test.name)
    }

    class Test(text: String) {
        val name = text
    }
}
