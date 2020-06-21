package learning.mahmoudmabrok.englishtime.feature.feature.current.grammer

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_grammer.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.GrammerItem
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.GrammerLesson
import learning.mahmoudmabrok.englishtime.feature.feature.current.aorb.IsAOrB
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.FinshGame
import learning.mahmoudmabrok.englishtime.feature.utils.SoundHelper
import learning.mahmoudmabrok.englishtime.feature.utils.log
import learning.mahmoudmabrok.englishtime.feature.utils.openActivity
import learning.mahmoudmabrok.englishtime.feature.utils.show


class GrammerActivity : BasicActivity() {


    var currentGrammer = 0
    var currentGrammerItem = 0

    lateinit var grammers: List<GrammerLesson>
    lateinit var currentLessoen: GrammerLesson
    lateinit var listItems: List<GrammerItem>


    /**
     * this will be called after finish
     */
    override fun goToNext() {
        openActivity(IsAOrB::class.java) {
            putInt(Constants.UNIT, unitNum)
            putInt(Constants.SCORE_KEY, score + prevScore)
        }
        // so no back
        finish()
    }

    override fun retryGame() {
        gameTotalScore = 0
        score = 0
        tvScoreForm.animateTo(score, 1000)


        startGame()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammer)

        startGame()
    }

    private fun startGame() {
        if (intent.hasExtra(Constants.UNIT)) {
            val unit = DataSet.getGrammer(intent.getIntExtra(Constants.UNIT, 0))
            if (unit == null) {
                this.show("No Grammer for This Unit")
                goToNext()
            } else {
                grammers = unit
                gameTotalScore += grammers.flatMap { it.toGrammerItems() }.size * 1
                loadLesson()
                handleCOnvertCLick()
            }
        }

        tvScoreForm.setMessage("Score:: ")
        tvScoreForm.animateTo(score, 1000)
    }

    private fun loadLesson() {
        try {
            currentLessoen = grammers[currentGrammer]
            listItems = currentLessoen.toGrammerItems()
            tvGrammerName.text = currentLessoen.name
            tvGrammerDescription.text = currentLessoen.description

            loadNextItem()
        } catch (e: Exception) {
            finishGame()
        }
    }

    private fun finishGame() {
        FinshGame.showFinish(this, R.id.home, score, gameTotalScore)
    }

    private fun handleCOnvertCLick() {
        tvCOnvertGrammer.setOnClickListener {
            checkAnswer()
        }
    }

    private fun checkAnswer() {
        val userAnswer = tvGrammerEnd.text.toString().trim()
        if (userAnswer == listItems[currentGrammerItem].end) {
            tvScoreForm.updateValue(Constants.SCORE_UNIT, 1000)
            SoundHelper.playCorrect(this)
        } else {
            SoundHelper.playFail(this)
        }
        currentGrammerItem += 1

        "checkAnswer $currentGrammerItem  ${listItems.size} $currentGrammer".log(mTag)

        if (currentGrammerItem < listItems.size) {
            loadNextItem()
        } else {
            currentGrammer += 1
            // reset items
            currentGrammerItem = 0
            if (currentGrammer < grammers.size) {
                loadLesson()
            } else {
                finishGame()
            }
        }
    }

    private fun loadNextItem() {
        // set start of grammar
        tvGrammerStart.text = listItems[currentGrammerItem].start
        // clear end
        tvGrammerEnd.setText("")
    }

}

private fun GrammerLesson.getItems(): List<GrammerItem> {
    return this.examples.split(" ").map {
        val list = it.split("#")
        GrammerItem(list[0], list[1])
    }
}
