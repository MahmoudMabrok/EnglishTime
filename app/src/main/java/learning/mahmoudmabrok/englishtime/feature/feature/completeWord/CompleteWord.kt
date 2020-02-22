package learning.mahmoudmabrok.englishtime.feature.feature.completeWord

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_complete_word.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.utils.dismissKeyboard
import learning.mahmoudmabrok.englishtime.feature.utils.log
import learning.mahmoudmabrok.englishtime.feature.utils.show
import kotlin.random.Random

class CompleteWord : AppCompatActivity() {

    val data = listOf("play", "score", "winner")
    var current = 0
    var lengthToMissed = 1

    val adapter: CompleteWordAdapter = CompleteWordAdapter(getSplitedData(), lengthToMissed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_word)

        rvCompleteWord.adapter = adapter

        btnCHeckCompleteWord.setOnClickListener {
            checkAnswer()
            this.dismissKeyboard()
        }

        loadData()

    }

    private fun loadData() {
        adapter.setData(getSplitedData())
        "count ${adapter.count} ${getSplitedData().size}".log()
    }

    private fun checkAnswer() {
        val word = String(adapter.data.toCharArray())
        val isSame = data[current] == word
        if (isSame) {
            current += 1
            lengthToMissed += 1
            this.show("Right")
            try {
                loadData()
            } catch (e: Exception) {
                this.show("Finish")
                finish()
            }
        } else {
            this.show("Wrong")
        }

    }

    /**
     * form word to list of chars and make some of them missed
     */
    private fun getSplitedData(): MutableList<Char> {
        val cur = data[current]
        print("before missed $cur")
        val d = getRandomMissed(cur)
        print("after missed $d")
        return d
    }

    private fun getRandomMissed(cur: String): MutableList<Char> {
        val d = cur.toCharArray().toMutableList()
        var rnd: Int
        for (i in 0 until lengthToMissed) {
            rnd = Random.nextInt(cur.length)
            d[rnd] = ' '
        }
        return d
    }

    private fun play() {
    }

}
