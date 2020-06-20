package learning.mahmoudmabrok.englishtime.feature.datalayer.models

import org.junit.Test

import org.junit.Assert.*

class CategoryTest {

    val category1 = Category(words = "a b c", name = "")
    val category2 = Category(words = "a B c", name = "")

    @Test
    fun `getWords() split it and get list `() {
        val expected = listOf("a", "b", "c")

        val result = category1.getWords()

        assertEquals(expected, result)
    }


    @Test
    fun `getWords() split it and get list and lower case  `() {
        val expected = listOf("a", "b", "c")

        val result = category2.getWords()

        assertEquals(expected, result)
    }

    @Test
    fun `list() split it and get list and lower case  `() {
        val input = listOf("aaa", "be", "cwwweq")
        val expected = listOf("be", "aaa", "cwwweq")

        val result = input.sortedBy { it.length }

        assertEquals(expected, result)
    }

    @Test
    fun `list() split it and get list and lower case ddd `() {
        val input = listOf("aaa", "be", "", "cwwweq", "a")
        val expected = listOf("", "a", "be", "aaa", "cwwweq")

        val result = input.sortedBy { it.length }

        assertEquals(expected, result)
    }




}