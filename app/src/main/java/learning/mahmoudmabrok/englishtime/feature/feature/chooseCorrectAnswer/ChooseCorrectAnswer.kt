package learning.mahmoudmabrok.englishtime.feature.feature.chooseCorrectAnswer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_choose_correct_answer.*
import learning.mahmoudmabrok.englishtime.R
import kotlin.random.Random

class ChooseCorrectAnswer : AppCompatActivity() {

    val data = listOf("play", "score", "winner")
    var current = 0
    var lengthToMissed = 1

    val adapter = CompleteWordAdapter(getSplitedData(), lengthToMissed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_correct_answer)
        rvCompleteWord.adapter = adapter



        btnCHeckCompleteWord.setOnClickListener {
            // todo check answers with update score
            current += 1
            // go to next
            lengthToMissed += 1
            adapter.setData(getSplitedData())

        }
    }

    /**
     * form word to list of chars and make some of them missed
     */
    private fun getSplitedData(): MutableList<Char> {
        val cur = data[current]
        val d = getRandomMissed(cur)
        return d
    }

    private fun getRandomMissed(cur: String): MutableList<Char> {
        val d = cur.toCharArray().toMutableList()
        var rnd = 0
        for (i in 0 until lengthToMissed) {
            rnd = Random.nextInt(cur.length)
            d[rnd] = ' '
        }
        return d
    }

    private fun play() {
    }

}
