package learning.mahmoudmabrok.englishtime.feature.datalayer.models

data class IsAItem(val types: List<String>, val values: List<String>, val answers: List<Int>)

data class Word(val word: String, val mean: String) {

    fun getReflected(): Word = Word(mean, word)

    fun isSame(other: Word): Boolean = word == other.mean
}


data class Category(
        val name: String,
        val words: String) {
    fun getWords(): List<String> {
        return words.split(" ")
    }
}


data class PunctuateItem(val expected: String, val actual: String)

data class GrammerItem(val start: String, val end: String)


data class GrammerLesson(val name: String,
                         val description: String,
                         val examples: String) {

}

data class Structure(val name: String, val examples: String) {
    fun getItems(): List<String> {
        return examples.split("#")
    }
}