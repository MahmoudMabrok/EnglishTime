package learning.mahmoudmabrok.englishtime.feature.datalayer

import learning.mahmoudmabrok.englishtime.feature.datalayer.models.*

object DataSet {

    private val isAdata: MutableList<IsAItem> = mutableListOf()
    private val categories: MutableList<List<Category>> = mutableListOf()
    private val puncates: MutableList<List<PunctuateItem>> = mutableListOf()
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
                Category("Musical Instruments", "Concert MusicRoom Bass Flute Drums Cello Xylophone Recorder Trumpet Cymbals"),//10
                Category("Seasons of year", "spring autumn summer winter"), // 4
                Category("NA", "Tiger parrot eagle"))
        )

        // need categories
        categories.add(listOf(
                Category("Animals", "Tiger parrot eagle deer camel panda bear lion giraffe kangaroo"), //10
                Category("Missc", "Emergency address officer operator wild animal"), // 6
                Category("NA", "Concert MusicRoom Bass"))
        )


        categories.add(listOf(
                Category("Cities", "Rome NewYorkCity Cairo Paris Tokyo HongKong London Honolulu Seoul SanFrancisco"), // 10
                Category("Months of the years", "January February march April may June July august September October November December"),// 12
                Category("Places", "Department store museum theatre statueOfLiberty  DisneyWorld universalStudios"),
                Category("NA", "Tiger parrot eagles"))

                //boarding theatre,PLaces
        )

        puncates.add(listOf(
                PunctuateItem("She played  the cello softly.", "she played  the cello softly"), // 2
                PunctuateItem("Rana played the drums badly.", "rana played the drums badly"),// 2
                PunctuateItem("Can he ride the bike quickly?", "can he ride the bike quickly"),// 2
                PunctuateItem("Ali is ahappy boy.", "ali is ahappy boy")// 2
        ))


        puncates.add(listOf(
                PunctuateItem("What  are you doing,Karim?", "what  are you doing,karim",3),
                PunctuateItem("Can I help you?", "can I help you"),// 2
                PunctuateItem("I'm cleaning up the music room.", "i'm cleaning up the music room"),// 2
                PunctuateItem("Youssef was the soloist on the violin.", "youssef was the soloist on the violin")// 2
        ))


        puncates.add(listOf(
                PunctuateItem("These wild animals escaped from the zoo.", "these wild animals escaped from the zoo"),// 2
                PunctuateItem("An eagle is flying above the giraffe.", "an eagle is flying above the giraffe"),// 2
                PunctuateItem("A kangaroo is hopped behind Maria.", "a kangaroo is hopped behind maria"),// 2
                PunctuateItem("A gaint panda are near Mustafa's house.", "a gaint panda are near mustafas house",4)
        ))


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
    fun getPuncatuate(x: Int): List<PunctuateItem> = puncates[x]
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