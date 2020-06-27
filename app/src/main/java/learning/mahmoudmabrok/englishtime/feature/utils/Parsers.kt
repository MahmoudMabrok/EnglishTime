package learning.mahmoudmabrok.englishtime.feature.utils

import learning.mahmoudmabrok.englishtime.feature.datalayer.models.PunctuateItem
import learning.mahmoudmabrok.englishtime.feature.feature.current.puncuate.Puncate

fun main() {

    val input = "He is playing football.*he is playing football\n" +
            "they are playing football *They are playing football.\n" +
            "they were playing football Yesterday*They were playing football , yesterday.\n" +
            "i can speak english*I can speak English.\n" +
            "He cannot speak English.** he cannot speak english\n" +
            "i could speak english*I could speak English.\n" +
            "they could not speak english.*They could  not  speak English"

    val parser = PuncatuateParse()
    val res = parser.parse(input)

    print(res)
}

interface BaseParser {
    abstract fun parse(input: String): String
}

class PuncatuateParse : BaseParser {
    /*
she was watching tv at 8 oclock yesterday*She was watching TV at 8 o'clock yesterday.
they were feeding the birds when I walk *They were feeding the birds, when I walk.

PunctuateItem("She was watching TV at 8 o'clock yesterday." ,"she was watchingtv at 8 oclock yesterday" , 26 ) ,
PunctuateItem("They were feeding the birds, when I walk." ,"they were feeding the birds when I walk " , 15 ) ,


He is playing football.*he is playing football
they are playing football *They are playing football.
they were playing football Yesterday*They were playing football , yesterday.
i can speak english*I can speak English.
He cannot speak English.** he cannot speak english
i could speak english*I could speak English.
they could not speak english.*They could  not  speak English


*/


    override fun parse(input: String): String {
        val lines =
                // replace all +1 spaces with one space
                input.replace("( )+".toRegex(), " ")
                        // then split it to lines each one ended with new line
                        .split("\n")
                        // then trim it to remove any trailing spaces
                        .map { it.trim() }

        val puncatuatePairs = lines.map { line ->
            // println("line $line")
            val lineparts = line.split("*")
            val wrong = lineparts.firstOrNull() ?: ""
            val correct = lineparts.lastOrNull() ?: ""
            val diffs =
                    // combine two strings so we have paris first-first char and so one
                    correct.zip(wrong)
                            // then filter items that not match each other, return size(count)
                            .filter {
                                // check if both are different then at last increase size with 1 as
                                // (zip combine as shorttest list so last one not compared )
                                it.first != it.second
                            }.size + 1
            "PunctuateItem(\"$correct\" ,\"$wrong\" , $diffs ) ,"
        }

        return puncatuatePairs.joinToString(separator = "\n")
    }


}