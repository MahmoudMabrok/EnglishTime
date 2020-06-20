package learning.mahmoudmabrok.englishtime.feature.utils

import org.junit.Test

import org.junit.Assert.*

class UtilsTest {

    @Test
    fun getPercentage() {
        val score = 10
        val total = 20

        val expected = 50

        val result = Utils.getPercentage(score, total)

        assertEquals(expected, result)
    }
}