package learning.mahmoudmabrok.englishtime.feature.datalayer

import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Category
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.GrammerLesson
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.IsAItem
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.PunctuateItem
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Structure
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Word

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


        // 6
        categories.add(listOf(
                Category("Musical Instruments", "Concert MusicRoom Bass Flute Drums Cello Xylophone Recorder Trumpet Cymbals"),//10
                Category("Seasons of year", "spring autumn summer winter"), // 4
                Category("NA", "Tiger parrot eagle"))
        )

        // 7
        categories.add(listOf(
                Category("Animals", "Tiger parrot eagle deer camel panda bear lion giraffe kangaroo"), //10
                Category("Misc", "Emergency address officer operator wild animal"), // 6
                Category("NA", "Concert MusicRoom Bass"))
        )


        /*    // 8
            categories.add(listOf(
                    Category("Cities", "Rome NewYorkCity Cairo Paris Tokyo HongKong London Honolulu Seoul SanFrancisco"), // 10
                    Category("Months of the years", "January February march April may June July august September October November December"),// 12
                    Category("Places", "Department store museum theatre statueOfLiberty  DisneyWorld universalStudios"),// 7
                    Category("NA", "Tiger parrot eagles"))
            )*/

        // 8
        categories.add(listOf(
                Category("Board", "letter chair sorry hard easy hundred why how"), // 8
                Category("On the board", " letme easyfor passout"), // 3
                Category("NA", " Cairo Paris Tokyo"))
        )

        // 9
        categories.add(listOf(
                Category("Unit 9 ", "Captain interview female pilot copilot airline aboardflight overseas flight onethird newspapers million available tour information thousand " +
                        "People daily hotel room basketball ticket boat walkingtours " +
                        "Boattours bustourrs"),
                Category("NA", "Tiger parrot Concert may"))
        )

        // 10
        categories.add(listOf(
                Category("Unit 10 ", "programme Africa gorilla comeover news besides fun funny afternoon football film match gamebell borrow today's special positive mood beef " +
                        "website findout begin satellite Egyptian invent videogames percent internet TV broadcasting remotecontrol electriccomputer history million black white first"),
                Category("NA", "Tiger parrot Concert may Department store museum theatre"))
        )


        // 6
        puncates.add(listOf(
                PunctuateItem("Now, he plays the harp.", "now he plays the harp", 3),
                PunctuateItem("He doesn't hit the drum.", "he doesnt hit the drum", 3),
                PunctuateItem("We don't blow the trumpt.", "we dont blow the trumpet", 3),
                PunctuateItem("Last year, he played the cello.", "last year,he played the cello", 3),
                PunctuateItem("Last year, she played the harp.", "last year,she played the harp", 3),
                PunctuateItem("Last year, she blow the trumpet.", "last year,she blow the trumpet", 3)
        ))


        puncates.add(listOf(
                PunctuateItem("She was watching TV at 8 o'clock yesterday.", "she was watching tv at 8 oclock yesterday", 4),
                PunctuateItem("They were feeding the birds, when I walk.", "they were feeding the birds when I walk ", 3)
        ))


        puncates.add(listOf(
                PunctuateItem("These wild animals escaped from the zoo.", "these wild animals escaped from the zoo"),// 2
                PunctuateItem("An eagle is flying above the giraffe.", "an eagle is flying above the giraffe"),// 2
                PunctuateItem("A kangaroo is hopped behind Maria.", "a kangaroo is hopped behind maria"),// 2
                PunctuateItem("A gaint panda are near Mustafa's house.", "a gaint panda are near mustafas house", 4)
        ))


        // 9
        puncates.add(listOf(
                PunctuateItem("There is no statically significant difference between the expert mental and groups means.",
                        "there is no statically significant difference between the expert mental and groups means"),// 2

                PunctuateItem("The bear in the zoo was a big one.", "the bear in the zoo was a big one") // 2

        ))


        // 10
        puncates.add(listOf(
                PunctuateItem("How much coffee do you drink?", "how much coffee do you drink"),// 2
                PunctuateItem("They were there for one week.", "they were there for one week") // 2

        ))


        gammers.add(listOf(

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


        // 9
        gammers.add(
                listOf(
                        GrammerLesson("in", "In تأتى معها سنه", "My birthday in May.#", oneWay = true),
                        GrammerLesson("at", "Atتأتي مع الساعة", "At two o'clock.#", oneWay = true),
                        GrammerLesson("on", "Atتأتي مع يوم", "On I meet him on Monday#", oneWay = true),
                        GrammerLesson("السؤال  بمعنى  ما المدة ومتى في الماضي", "When +did+sub+ inf? ",
                                "When did Omar go to Hong Kong?#", oneWay = true),
                        GrammerLesson("structure", "فاعل+was#were+for++مدة زمنية", "I was there for one week#", oneWay = true)


                )
        )


        // 10
        gammers.add(
                listOf(
                        GrammerLesson("قاعده if الحاله الاولى", "قاعده if الحاله الاولى  تعبر عن احتما ل حدوث شى فى المستقبل\n" +
                                "If+مضارع  بسيط +مستقبل بسيط", "If  he runs , he will win the race #If I study well', I will  get  high marks", oneWay = true)
                )
        )


        // 6
        structures.add(listOf(
                Structure("Adverb", "Good*well#bad*badly#quiet*quietly#loud*loudly#quick*quicly#slow*slowly#happy*happily#sad*sadly#soft*softly"),
                Structure("preposition", "...school*At# a lot..*of# ... the first year*in# ... the violin*on"),
                Structure("Verbs and nouns", "... a performance*Give# ... a party*have# ... music*perform# ... a play*perform"))
        )

        // 7
        structures.add(listOf(
                Structure("Verbs and nouns", "Climb tree# eat a fish# take a nap# use sun screen# put on makeup# wash the car# read anew paper# feed the birds# chop vegetables# watch TV# climb tree# take out the rubbish")
        ))

        // 8
        structures.add(null)

        // 9
        structures.add(listOf(
                Structure("Expressions", "Would you like ='d like#Go ahead#by yourself#thanks for your time#my pleasure"),
                Structure("Verbs", "Interview interviewed# ask asked#visit visited#thank thanked #fly flew#have had"),
                Structure("Places", "Rome#new York city#Cairo#Paris#Tokyo#hang Kong#London# Honolulu"),
                Structure("Months of the year", "January# February# march# April #may# June# July# august# September# October#November#December ")
        )
        )

        // 10
        structures.add(listOf(
                Structure("Adjectives", "real# good # cool# bad# hungry# nervous# tired#embarrassed# happy#sad# disappointed#cold hot#proud"),
                Structure("Exxpresssions", "what was on?# On TV # too bad#i missed it #me, neither# time for class #a school night"),
                Structure("Verbs + Nouns", "Skip lunch# go to bed late#  get a good mark#make a mistake# takeoff my jacket#  win aprize #turn off the  fan  # forget my homework# lose my favorite pencil# fall my chair# go on sale#found out#launch")
        ))

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