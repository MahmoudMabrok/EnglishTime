package learning.mahmoudmabrok.englishtime.feature.datalayer

import learning.mahmoudmabrok.englishtime.feature.datalayer.models.*

object DataSet {

    private val isAdata: MutableList<IsAItem> = mutableListOf()
    private val categories: MutableList<List<Category>> = mutableListOf()
    private val puncates: MutableList<PunctuateItem> = mutableListOf()
    private val gammers: MutableList<List<GrammerLesson>?> = mutableListOf()
    private val structures: MutableList<List<Structure>?> = mutableListOf()


    init {
        isAdata.add(IsAItem(listOf("Verb", "Noun"), listOf("Ahmed", "is", "Mohamed", "play"), listOf(1, 0, 1, 0)))
        isAdata.add(IsAItem(listOf("Adjective", "Adverb"), listOf("Ahmed", "is", "Mohamed", "play"), listOf(1, 0, 1, 0)))
        isAdata.add(IsAItem(listOf("Verb", "Noun"), listOf("Ahmed", "is", "Mohamed", "play"), listOf(1, 0, 1, 0)))
        isAdata.add(IsAItem(listOf("Verb", "Noun"), listOf("Ahmed", "is", "Mohamed", "play"), listOf(1, 0, 1, 0)))
        isAdata.add(IsAItem(listOf("Adjective", "Adverb"), listOf("Ahmed", "is", "Mohamed", "play"), listOf(1, 0, 1, 0)))
        isAdata.add(IsAItem(listOf("Verb", "Noun"), listOf("Ahmed", "is", "Mohamed", "play"), listOf(1, 0, 1, 0)))


        categories.add(listOf(
                Category("Musical Instruments", "Concert MusicRoom Bass Flute Drums Cello Xylophone Recorder Trumpet Cymbals"),
                Category("Seasons of year", "spring fall autumn summer winter"),
                Category("NA", "Tiger parrot eagle"))
        )

        // need categories
        categories.add(listOf(
                Category("Animals", "Tiger parrot eagle deer camel panda bear lion giraffe kangaroo"),
                Category("Missc", "Emergency address officer opertator wild animal"),
                Category("NA", "Concert MusicRoom Bass"))
        )


        categories.add(listOf(
                Category("Cities", "Rome NewYorkCity Cairo Paris Tokyo hongkong London Honolulu Seoul sanFrancisco"),
                Category("Months of the years", "January February march April may June July august September October November December"),
                Category("Places", "Department store museum theatre statueOfLiberty boarding theatre DisneyWorld universalStudios"),
                Category("NA", "Tiger parrot eagles"))
        )


        puncates.add(PunctuateItem("How are you?", "how are you"))
        puncates.add(PunctuateItem("Ahmed went school with Ali.", "ahmed went school with ali"))
        puncates.add(PunctuateItem("That was amazing!", "hhat was amazing"))




        gammers.add(listOf(
                GrammerLesson("Adverb:", "الحال يصف الفعل وياتى بعده ",
                        "Good#well badl#badly quiet#quietly loud#loudly quick#quicly slow#slowly happy#happily sad#sadly soft#softly"
                ),

                GrammerLesson("Present simple", "\u202Bمتكرر\u202C \u202Bفعل\u202C \u202Bاو\u202C \u202Bعادة\u202C \u202Bاو\u202C \u202Bحقيقه\u202C \u202Bعن\u202C \u202Bللتعبير\u202C \u202Bيستخدم\u202C",
                        "play#plays watch#watches work#works"),

                GrammerLesson("Past simple", "\u202Bالماضى\u202C \u202Bفى\u202C \u202Bوانتهى\u202C \u202Bشئ\u202C \u202Bعن\u202C \u202Bللتعبير\u202C \u202Bيستخدم\u202C",
                        "Perform#performed give#gave write#wrote sing#sang")


        ))


        gammers.add(listOf(
                GrammerLesson("\u202Bالصفات\u202C \u202Bمقارنه\u202C", "\u202Bما\u202C \u202Bصفه\u202C \u202Bفى\u202C \u202Bاتين\u202C \u202Bبين\u202C \u202Bمقارنه\u202C \u202Bعند\u202C \u202Bالصفات\u202C \u202Bستخدم\u202C",
                        "Small#smaller fast#faster slow#slower quick#quicker"),

                GrammerLesson("\u202Bالمستمر\u202C \u202Bالماضى\u202C", "حدث استمر فى الماضي وقطعه احدث اخر فى الماضي البسيط ",
                        "play#playing watch#watching")
        ))

        gammers.add(null)

        structures.add(listOf(
                Structure("preposition", "At school# a lot of# in the first year# on the violin"),
                Structure("Verbs and nouns", "Give a performance# have a party# perform music# perform a play"))
        )

        structures.add(listOf(
                Structure("Verbs and nouns", "Climb tree# eat a fish# take a nap# use sun screen# put on makeup# wash the car# read anew paper# feed the birds# chop vegetables# watch TV# climb tree# take out the rubbish")
        ))

        structures.add(null)


    }

    fun getIsA(x: Int): IsAItem = isAdata[x]
    fun getCategory(x: Int): List<Category> = categories[x]
    fun getPuncatuate(x: Int): PunctuateItem = puncates[x]
    fun getGrammer(x: Int): List<GrammerLesson>? = gammers[x]
    fun getStructure(unitNum: Int): List<Structure>? = structures[unitNum]

}


class DataSource {

    companion object {
        val data = listOf(
                Word("Do", "يفعل"),
                Word("Play", "يلعب"),
                Word("Work", "يعمل")
        )


    }
}