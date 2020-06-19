package learning.mahmoudmabrok.englishtime.feature.utils

import org.junit.Test

import org.junit.Assert.*

class CharacterUtilTest {

    @Test
    fun splitWord() {
    }

    @Test
    fun `getNumOfMissed() with empty list return 0 `() {
        // arrange
        val input = mutableListOf<Char>()
        val expected = 0

        // act
        val result = CharacterUtil.getNumOfMissed(input)

        //assert
        assertEquals(expected, result)
    }

    @Test
    fun `getNumOfMissed() with list return 1 `() {
        // arrange
        val input = mutableListOf<Char>('a', ' ', 'b')
        val expected = 1

        // act
        val result = CharacterUtil.getNumOfMissed(input)

        //assert
        assertEquals(expected, result)
    }

    @Test
    fun `getNumOfMissed() with list return 3 `() {
        // arrange
        val input = mutableListOf<Char>('a', ' ', 'b', ' ', 'b', ' ', 'b')
        val expected = 3

        // act
        val result = CharacterUtil.getNumOfMissed(input)

        //assert
        assertEquals(expected, result)
    }


}