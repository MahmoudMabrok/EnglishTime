package learning.mahmoudmabrok.englishtime.feature.datalayer.models

import org.junit.Test

import org.junit.Assert.*

class StructureTest {

    @Test
    fun toItems() {
        val item = Structure("a", "a qA*a    a")
        val expected = listOf(StructureItem("a", "a"))

        //act
        val result = item.toItems()

        assertEquals(expected, result)
    }
}