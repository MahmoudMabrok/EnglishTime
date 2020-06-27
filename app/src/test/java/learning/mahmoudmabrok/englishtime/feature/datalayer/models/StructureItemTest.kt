package learning.mahmoudmabrok.englishtime.feature.datalayer.models

import org.junit.Test

import org.junit.Assert.*

class StructureItemTest {

    @Test
    fun `getFullSentence() with ony way word `() {

        val input = StructureItem(src = "good", answer = "well")
        val res = input.fullSentence
        val expected = "well"

        assertEquals(expected, res)
    }

    @Test
    fun `getFullSentence() with two way word `() {

        val input = StructureItem(src = "good ......", answer = "at")
        val res = input.fullSentence
        val expected = "good at"

        assertEquals(expected, res)
    }


}