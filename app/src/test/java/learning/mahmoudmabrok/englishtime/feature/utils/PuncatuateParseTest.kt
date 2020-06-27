package learning.mahmoudmabrok.englishtime.feature.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class PuncatuateParseTest {

    @Test
    fun parse() {
        val input = "She played  the cello softly.*she played  the cello softly"
        val expected = "aa a"
        val result = PuncatuateParse().parse(input)

        assertEquals(expected, result)
    }
}