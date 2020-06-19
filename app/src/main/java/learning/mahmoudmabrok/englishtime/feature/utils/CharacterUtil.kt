package learning.mahmoudmabrok.englishtime.feature.utils

object CharacterUtil {

    fun splitWord(num: Int, word: String) {
        val randms = IntRange(0, word.length - 1)
    }

    fun getNumOfMissed(list: MutableList<Char>): Int {
        return list.filter { it == ' ' }.size
    }
}