package learning.mahmoudmabrok.englishtime.feature.datalayer.models

import org.junit.Test

import org.junit.Assert.*

class StructureTest {

    @Test
    fun toItems() {
        val item = Structure("a", "A#bA a    A")
        val expected = listOf(StructureItem("a", "a"), StructureItem("ba a a", "ba a a"))

        //act
        val result = item.toItems()

        assertEquals(expected, result)
    }
}