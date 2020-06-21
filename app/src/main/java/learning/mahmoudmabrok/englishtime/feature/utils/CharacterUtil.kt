package learning.mahmoudmabrok.englishtime.feature.utils

import java.util.*
import kotlin.random.Random

object CharacterUtil {

    /**
     * split word based on num is required
     * return same string ig num is equal or more length of word
     */
    fun splitWord(num: Int, word: String): MutableList<Char> {
        val numberMissed = if (num >= word.length) word.length - 1 else num

        // first prepare random index to be removed
        val setOfNums = mutableSetOf<Int>()
        val rand = Random(Date().time)

        while (setOfNums.size < numberMissed)
            setOfNums.add(rand.nextInt(word.length))


        return word.mapIndexed { index, c -> if (index in setOfNums) ' ' else c }.toMutableList()
    }

    fun getNumOfMissed(list: MutableList<Char>): Int {
        return list.filter { it == ' ' }.size
    }
}