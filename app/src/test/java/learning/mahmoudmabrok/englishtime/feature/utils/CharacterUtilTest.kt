package learning.mahmoudmabrok.englishtime.feature.utils

import org.junit.Test

import org.junit.Assert.*

class CharacterUtilTest {

    @Test
    fun `splitWord() with  list and ask to remove more length `() {
        // arrange
        val input = "aa"
        val numToRemove = 3
        val expected = input.length

        // act
        val wordAfter = CharacterUtil.splitWord(word = input, num = numToRemove)
        val result = wordAfter.size

        //assert
        assertEquals(expected, result)

    }

    @Test
    fun `splitWord() with  list remove 2 out of  5  `() {
        // arrange
        val input = "ahmed"
        val numToRemove = 2
        val expected = 2

        // act
        val wordAfter = CharacterUtil.splitWord(word = input, num = numToRemove)
        val result = CharacterUtil.getNumOfMissed(wordAfter)

        //assert
        assertEquals(expected, result)
    }

    @Test
    fun `splitWord() with  list remove 4 out of  5  `() {
        // arrange
        val input = "ahmed"
        val numToRemove = 4
        val expected = 4

        // act
        val wordAfter = CharacterUtil.splitWord(word = input, num = numToRemove)
        val result = CharacterUtil.getNumOfMissed(wordAfter)

        //assert
        assertEquals(expected, result)
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