package learning.mahmoudmabrok.englishtime.feature.utils

import learning.mahmoudmabrok.englishtime.feature.datalayer.models.PunctuateItem
import learning.mahmoudmabrok.englishtime.feature.feature.current.puncuate.Puncate

fun main() {

    val parser = PuncatuateParse()
    val res = parser.parse("now he plays the harp*Now, he plays the harp.\n" +
            "he doesnt hit the drum*  He doesn't hit the drum.\n" +
            "we dont blow the trumpet* We don't  blow  the trumpt.\n" +
            "Last year,he played the cello.*\tlast year he played the cello. \n" +
            "\t\tlastyear, she played the harp* Last year, she played the harp.\n" +
            "last year, she played the harp*last year,she blow the trumpet.\n")

    print(res)
}

interface BaseParser {
    abstract fun parse(input: String): String
}

class PuncatuateParse : BaseParser {
    /*

    now he plays the harp*Now, he plays the harp.
    he doesnt hit the drum*  He doesn't hit the drum.
    we dont blow the trumpet* We don't  blow  the trumpt.
    Last year,he played the cello.*	last year he played the cello.
    lastyear, she played the harp* Last year, she played the harp.
    last year, she played the harp*last year,she blow the trumpet.


*/


    override fun parse(input: String): String {
        val lines =
                // replace all +1 spaces with one space
                input.replace("( )+".toRegex(), " ")
                        // then split it to lines each one ended with new line
                        .split("\n")
                        // then trim it to remove any trailing spaces
                        .map { it.trimEnd() }

        val puncatuatePairs = lines.map { line ->
            val lineparts = line.split("*")
            val wrong = lineparts.firstOrNull() ?: ""
            val correct = lineparts.lastOrNull() ?: ""
            val diffs =
                    // combine two strings so we have paris first-first char and so one
                    correct.zip(wrong)
                            // then filter items that not match each other, return size(count)
                            .filter { it.first != it.second }.size
            "PunctuateItem(\"$correct\" ,\"$wrong\" , $diffs ) ,"
        }

        return puncatuatePairs.joinToString(separator = "\n")
    }


}