package learning.mahmoudmabrok.englishtime.feature.feature.crossword

data class CrossGame(val items: List<CrossItem>, val size: Int) {

    fun make(): CharArray {
        val data = CharArray(size)
        data.fill('#')
        for (cross in items) {

            for ((index, ch) in cross.word.toCharArray().withIndex()) {
                println("ch :$ch")
                data[cross.start + (index * cross.updateAmount)] = ch

            }
        }
        println("final data $data")
        return data
    }

    fun getHints(): List<Int> {
        return items.map { it.start }
    }

    fun getHintAsText(): List<String> {
        return items.map { it -> Pair(it.start, it.word) }
                .mapIndexed { index, pair -> "${(index + 1)} :: ${pair.second}\n" }

    }
}

data class CrossItem(val word: String, val start: Int, val updateAmount: Int)

fun List<String>.diff(other: List<String>): List<Int> {
    println("first $this")
    println("second $other")
    return this.filterIndexed { index, c -> other[index] != c }.map { this.indexOf(it) }
}

data class Differ(val l1: List<String>, val l2: List<String>) {
    fun getDiff(): String {
        println("test1 $l2")
        return l1.diff(l2).toString()
    }
}

class Keys {
    companion object {
        @JvmField
        val chars = ('a'..'z').toList()
    }
}