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
                Category("Musical Instruments", "Concert*MusicRoom*Bass*Flute*Drums*Cello*Xylophone*Recorder*Trumpet*Cymbals"),//10
                Category("Seasons of year", "spring*autumn*summer*winter"), // 4
                Category("NA", "Tiger*parrot*eagle"))
        )

        // 7
        categories.add(listOf(
                Category("Animals", "Tiger*parrot*eagle*deer*camel*panda*bear*lion*giraffe*kangaroo"), //10
                Category("NA", "Emergency*address*officer*operator*wildanimal*Parents* garden* home* street* home* cinema* wild *calm* help* take care of *my little sister* stay clam"))
        )

        // 8
        categories.add(listOf(
                Category("at the kindergarten", "Board*letter*chair*sorry*hard*easy*hundred*why*how"), // 8
                Category("NA", "Cookie*lookout*bee*behind*howoften*haircut*onceamonth*howaboutyou?*" +
                        "Moveaway*kindergarten*funny*break*soon*miss*enormous*makefriends*sad*new*little*old"))
        )

        // 9
        categories.add(listOf(
                Category("Cities around the world", "Captain*interview*female*pilot*copilot*airline*aboardflight*overseas*flight*onethird*newspapers*million*available*tour*information*thousand*" +
                        "People*daily*hotel*room*basketball*ticket*boat*walkingtours*" +
                        "Boattours*bustourrs"),
                Category("NA", "Tiger*parrot*Concert*may"))
        )

        // 10
        categories.add(listOf(
                Category("at school", "teacher*school*funny*electriccomputer*flag*table*board*playground*internet"),
                Category("NA", "programme*Africa*gorilla*comeover*news*besides*fun*afternoon*football*film*match*gamebell*borrow*today's*special*positive*mood*beef*" +
                        "website*findout*begin*satellite*Egyptian*invent*videogames*percent*TV*broadcasting*remotecontrol*history*million*black*white*first")))


        //region puncatuete
        // 6
        puncates.add(listOf(
                PunctuateItem("Now, he plays the harp.", "now he plays the harp", 3),
                PunctuateItem("He doesn't hit the drum.", "he doesnt hit the drum", 3),
                PunctuateItem("We don't blow the trumpt.", "we dont blow the trumpt", 3),
                PunctuateItem("Last year, he played the cello.", "last year,he played the cello", 3),
                PunctuateItem("Last year, she played the harp.", "last year,she played the harp", 3),
                PunctuateItem("Last year, she blow the trumpet.", "last year,she blow the trumpet", 3)
        ))

        // 7
        puncates.add(listOf(
                PunctuateItem("She was watching TV at 8 o'clock yesterday.", "she was watching tv at 8 oclock yesterday", 4),
                PunctuateItem("They were feeding the birds, when I walk.", "they were feeding the birds when I walk", 3)
        ))


        //8
        puncates.add(listOf(
                PunctuateItem("He is playing football.", "he is playing football", 2),
                PunctuateItem("They are playing football.", "they are playing football", 3),
                PunctuateItem("They were playing football , yesterday.", "they were playing football yesterday", 4),
                PunctuateItem("I can speak English.", "i can speak english", 3),
                PunctuateItem("He cannot speak english.", "he cannot speak english", 3),
                PunctuateItem("I could speak English.", "i could speak english", 3),
                PunctuateItem("They could not speak English", "they could not speak english ", 3)
        ))


        // 9
        puncates.add(listOf(
                PunctuateItem("When did Omar go to Hong Kong?", "when did omar go to hong kong ", 5),
                PunctuateItem("How was she there?", "how was she there ", 2)

        ))


        // 10
        puncates.add(listOf(
                PunctuateItem("If he runs , she will win the race.", "if he run she will win the race ", 3),
                PunctuateItem("If I study well, I will get high marks.", "if I study well I will get high marks", 3)

        ))
        //endregion


        gammers.add(listOf(

                GrammerLesson("Present simple", "\u202Bمتكرر\u202C \u202Bفعل\u202C \u202Bاو\u202C \u202Bعادة\u202C \u202Bاو\u202C \u202Bحقيقه\u202C \u202Bعن\u202C \u202Bللتعبير\u202C \u202Bيستخدم\u202C",
                        "play*plays#watch*watches#work*works"
                )

                ,

                GrammerLesson("Past simple", "\u202Bالماضى\u202C \u202Bفى\u202C \u202Bوانتهى\u202C \u202Bشئ\u202C \u202Bعن\u202C \u202Bللتعبير\u202C \u202Bيستخدم\u202C",
                        "Last year, she ...the harp.*played")

        ))


        gammers.add(listOf(
                GrammerLesson("\u202Bالصفات\u202C \u202Bمقارنه\u202C", "\u202Bما\u202C \u202Bصفه\u202C \u202Bفى\u202C \u202Bاتين\u202C \u202Bبين\u202C \u202Bمقارنه\u202C \u202Bعند\u202C \u202Bالصفات\u202C \u202Bستخدم\u202C",
                        "Small*smaller#fast*faster# slow*slower# quick *quicker# fat *fatter# big*bigger#thin*thinner"),

                GrammerLesson("الماضى المستمر", "حدث استمر فى الماضي وقطعه احدث اخر فى الماضي البسيط ",
                        "play*playing#watch*watching")
        ))

        gammers.add(listOf(
                GrammerLesson("Past Cont, Preent Cont.", "Vtobe past (was -were) \npresent (am –is- are)",
                        "He ...playing football*is#He... playing football, yesterday*was#They ...playing football*are#They... playing football. Yesterday*were"),
                GrammerLesson("Could—couldn't , can -cannot", "", "I ... speak English*can#He ... speak English*cannot#I ...speak*could#They ...speak English*could not")
        ))


        // 9
        gammers.add(
                listOf(
                        GrammerLesson("in", "In تأتى معها سنه", "My birthday ... May.*in"),
                        GrammerLesson("at", "Atتأتي مع الساعة", "... two o'clock*at"),
                        GrammerLesson("on", "on تأتي مع يوم", ".... I meet him on Monday*on"),
                        GrammerLesson("السؤال  بمعنى  ما المدة ومتى في الماضي", "When +did+sub+ inf? ", "... did Omar go to Hong Kong?*When"),
                        GrammerLesson("structure", "فاعل+was#were+for++مدة زمنية", "I was there ... one week*for")


                )
        )

        // 10
        gammers.add(
                listOf(
                        GrammerLesson("قاعده if الحاله الاولى", "قاعده if الحاله الاولى  تعبر عن احتما ل حدوث شى فى المستقبل\n" +
                                "If+مضارع  بسيط +مستقبل بسيط", "If he runs, he will ...the race.*win#If I study well', I will ...high marks*get")
                )
        )


        //region structures
        // 6
        structures.add(listOf(
                Structure("Adverb", "Good*well#bad*badly#quiet*quietly#loud*loudly#quick*quickly#slow*slowly#happy*happily#sad*sadly#soft*softly"),
                Structure("preposition", "...school*At# a lot..*of# ... the first year*in# ... the violin*on"),
                Structure("Verbs and nouns", "... a performance*Give# ... a party*have# ... music*perform# ... a play*perform"),
                Structure("Past Verbs", "Perform*performed#give*gave#write*wrote#sing*sang")
        )
        )

        // 7
        structures.add(listOf(
                Structure("Verbs and nouns", "...tree*climb#... a fish*eat#...a nap*take#...sun screen*use#... makeup*put on#...the car*wash#...anews paper*read#...the birds*feed#vegetable....*chop#watch*...TV#...tree*climb#...the rubbish*take out"),
                Structure("Past Verbs", "Wash* washed# climb *climbed# chop* chopped# hop* hopped# watch* watched# talk* talked# put *put# take* took #read* read# feed* fed# fly *flew# run* ran")
        ))

        // 8
        structures.add(listOf(
                Structure("Past Verbs", "Miss*missed*remember#remembered#want*wanted#live*lived"),
                Structure("Expressions", "... the board*on#...sorry*I'm#let —*me#easy ...*for#pass...*out#That's key come...*on#—work*good#...the board*clean"),
                Structure("Verbs + Nouns", "...the alphabet*say#... build a sandcastle*bluid#... the bubble*blow#... butterfly*catch#... an orange*peel#...a ball*throw#...to ten*count#...award*spell#...out heart*cut out#...English*speak")
        )

        )// 9
        structures.add(listOf(
                Structure("Past Verbs", "Interview*interviewed#ask*asked#visit*visited#thank*thanked#fly*flew#have*had"),
                Structure("Expressions", "...you like*would#I ... sure*'m#...ahead*go#...yourself*by#thanks ...your time*for"),
                Structure("Write Months of the year", "1*January# 2*February# 3*march#4*April#5*may# 6*June#7*July# 8*august# 9*September# 10*October#11*November#12*December ")
        )
        )

        // 10
        structures.add(listOf(
                Structure("Past Verbs", "Skip*skipped#turn off*turned off#turn on*turned on#dream*dreamed#win*won#fall*fell#lose*lost#takeoff*took off#forget*forgot#launch*launched#happen*happened#invent*invented#use*used#begin*began#found out*found out#know*knew"),
                Structure("Expressions", "... was on?*what#...TV*on#...bad*too#I missed...*it#..., neither*me#time ...class a school night*for"),
                Structure("Verbs + Nouns", "...lunch*skip#...to bed late*go#...a good mark*get#...a mistake*make#... my jacket*takeoff#...a prize turn off the fan*win#...my homework*forget#... my favorite pencil*lose#...my chair*fall#...on sale*go#found ...*out")
        ))


        //endregion

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