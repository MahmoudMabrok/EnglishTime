package learning.mahmoudmabrok.englishtime.feature.utils

fun main() {

    val input = "\n" +
            "-----tree*climb\n" +
            " ---- a fish* eat\n" +
            "  -----a nap*take* \n" +
            " ------sun screen*use\n" +
            " ---- makeup*put on\n" +
            " -------the car* wash\n" +
            "------anew paper*read\n" +
            " ------the birds*feed\n" +
            "vegetable....*chop\n" +
            "\twatch*\t------TV\n" +
            "-------tree*climb\n" +
            "----------the rubbish*take out"
    val parser = StrucutreParse()
    val res = parser.parse(input)

    print(res)
}

interface BaseParser {
    fun parse(input: String): String

    fun stringToLiines(input: String, mainSeperator: String = "\n"): List<String> {
        // replace all +1 spaces with one space
        return input.trimStart().replace("( )+".toRegex(), " ")
                // then split it to lines each one ended with new line
                .split(mainSeperator)
                // then trim it to remove any trailing spaces
                .map { it.trim() }
    }

    fun getPair(lines: List<String>, minorSep: String = "*"): List<Pair<String, String>> {
        return lines.map { line ->
            val lineparts = line.split(minorSep)
            val first = lineparts.firstOrNull() ?: ""
            val second = lineparts.lastOrNull() ?: ""
            Pair(first, second)
        }
    }
}

class StrucutreParse : BaseParser {
    /*

---- was on?*what#-----TV*on#----bad*too#I missed---*it#----, neither*me#time ----class a school night*for#
     */

    override fun parse(input: String): String {
        val lines = stringToLiines(input)
        val pairs = getPair(lines, "*")
        return pairs.map { "${it.first.trimStart().trimEnd()}*${it.second.trimStart().trimEnd()}" }.joinToString(separator = "#")
    }
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


PunctuateItem("he is playing football" ,"He is playing football." , 2 ) ,
PunctuateItem("They are playing football." ,"they are playing football " , 3 ) ,
PunctuateItem("They were playing football , yesterday." ,"they were playing football Yesterday" , 11 ) ,
PunctuateItem("I can speak English." ,"i can speak english" , 3 ) ,
PunctuateItem(" he cannot speak english" ,"He cannot speak English." , 24 ) ,
PunctuateItem("I could speak English." ,"i could speak english" , 3 ) ,
PunctuateItem("They could not speak English" ,"they could not speak english." , 3 )



if he run she will win the race*If he runs , she will win the race.
if I study well', I will get high marks*If I study well, I will get high marks.



*/

    override fun parse(input: String): String {
        val lines = stringToLiines(input)

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