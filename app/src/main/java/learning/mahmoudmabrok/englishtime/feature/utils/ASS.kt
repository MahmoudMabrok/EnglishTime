package learning.mahmoudmabrok.englishtime.feature.utils

import java.io.File
import java.io.FileNotFoundException
import java.io.PrintWriter
import java.math.BigInteger
import java.util.*


data class Lib(val nBooks: Int,
               val toSignUp: Int,
               val perDay: Int,
               val ids: List<Int>)

object Main {

    // val list = listOf("a_example.txt","b_read_on.txt","d_tough_choices.txt","c_incunabula.txt","e_so_many_books.txt","f_libraries_of_the_world.txt")
    val list = listOf("c_incunabula.txt")

    @JvmStatic
    fun main(args: Array<String>) {

        list.forEach {
            handleInput(it)
        }


    }

    private fun handleInput(name: String) {

        val nBooks: Int
        val nLibs: Int
        val days: Int
        var scores: MutableList<Int> = mutableListOf()
        var libs: MutableList<Lib> = mutableListOf()

        var input = Scanner(File(name))

        val firstLine = input.nextLine().split(" ")
        nBooks = Integer.parseInt(firstLine[0])
        nLibs = Integer.parseInt(firstLine[1])
        days = Integer.parseInt(firstLine[2])


        val seocndLIne = input.nextLine().split(" ")
        scores = seocndLIne.map { Integer.parseInt(it) }.toMutableList()

        var nLibBooks: Int
        var nSignUp: Int
        var nPerDay: Int
        var st: String
        for (i in 0 until nLibs) {
            val list = input.nextLine().split(" ").map { Integer.parseInt(it) }
            nLibBooks = list[0]
            nSignUp = list[1]
            nPerDay = list[2]

            val ids = input.nextLine().split(" ").map { Integer.parseInt(it) }.toList()
            libs.add(Lib(nLibBooks, nSignUp, nPerDay, ids))
        }

        libs.sortByDescending { it.perDay }

        try {
            val pr = PrintWriter(File("sol$name"))
            val count = nLibs
            pr.println("${count}")
            libs = libs.subList(0, count)
            libs.forEachIndexed { idx, it ->
                val to = (days - it.toSignUp) // days to ship
                println("to $to")
                var books: BigInteger = (to * it.perDay).toBigInteger()
                if (books > it.nBooks.toBigInteger()) {
                    books = it.nBooks.toBigInteger()
                }

                println("books $books , nBooks ${it.nBooks}")
                pr.println("$idx ${it.nBooks}")
                println("$idx ${it.nBooks}")
                println("$idx ${it.nBooks}")
                it.ids.take(it.nBooks).forEach { pr.print("$it ") }
                pr.println()
            }

            pr.close()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

}