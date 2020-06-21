package learning.mahmoudmabrok.englishtime.feature.datalayer.models

import java.util.*

data class IsAItem(val types: List<String>, val values: List<String>, val answers: List<Int>)

data class Word(val word: String, val mean: String) {

    fun getReflected(): Word = Word(mean, word)

    fun isSame(other: Word): Boolean = word == other.mean
}


data class Category(
        val name: String,
        val words: String) {
    fun getWords(): List<String> {
        return words.toLowerCase().split(" ")
    }
}


data class PunctuateItem(val expected: String, val actual: String, val numWrong:Int = 2 )

data class GrammerItem(val start: String, val end: String)


data class GrammerLesson(val name: String,
                         val description: String,
                         val examples: String,
                         val oneWay: Boolean = false) {
    fun toGrammerItems(): List<GrammerItem> {
        return if (oneWay) {
            examples.split("#").map { GrammerItem(it, "") }
        } else {
            examples.split(" ").map {
                val list = it.split("#")
                GrammerItem(list[0], list[1])
            }
        }
    }
}

data class Structure(val name: String, val examples: String) {
    fun getItems(): List<String> {
        return examples.split("#")
    }

    fun toItems(): List<StructureItem> {
        return examples.split("#").map {
            val parts = it.split("*")
            val src = parts.first().replace("\\s+".toRegex(), " ").toLowerCase()
            val answer = parts.last().replace("\\s+".toRegex(), " ").toLowerCase()
            StructureItem(src,
                    answer)
        }
    }
}


data class StructureItem(val src: String, val answer: String = "")