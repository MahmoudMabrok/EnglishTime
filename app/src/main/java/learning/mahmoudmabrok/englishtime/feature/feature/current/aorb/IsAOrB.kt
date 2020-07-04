package learning.mahmoudmabrok.englishtime.feature.feature.current.aorb

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_is_aor_b_alt.*

import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Structure
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.StructureItem
import learning.mahmoudmabrok.englishtime.feature.feature.current.categorizeWords.CategorizeWords
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.FinshGame
import learning.mahmoudmabrok.englishtime.feature.utils.SoundHelper
import learning.mahmoudmabrok.englishtime.feature.utils.dismissKeyboard
import learning.mahmoudmabrok.englishtime.feature.utils.openActivity


class IsAOrB : BasicActivity() {

    var currentStructureIndex = 0
    var currentStructureItemIndex = 0


    lateinit var structures: List<Structure>

    /**
     * hold current strcture items
     */
    lateinit var structureItems: List<StructureItem>

    var currentStructureItem: StructureItem? = null
    var currentStructure: Structure? = null

    /**
     * this will be called after finish
     */
    override fun goToNext() {
        openActivity(CategorizeWords::class.java) {
            putInt(Constants.UNIT, unitNum)
            putInt(Constants.SCORE_KEY, score + prevScore)
            putInt(Constants.OVERALL_TOTAL, gameTotalScore + overallTotal)
        }
        // so no back
        finish()
    }

    override fun retryGame() {
        gameTotalScore = 0
        supportFragmentManager.popBackStack()
        score = 0
        btnNext.visibility = View.VISIBLE
        currentStructureIndex = 0
        currentStructureItemIndex = 0
        startGame()
        scoreView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_is_aor_b_alt)

        unitNum = intent.getIntExtra(Constants.UNIT, 0)
        startGame()

        scoreView()

        setupSound()

        imPlaySound.setOnClickListener {
            playSound(currentStructureItem?.fullSentence ?: "")
        }

        btnNext.setOnClickListener {
            checkAnswer()
        }

    }

    private fun scoreView() {
        tvScoreForm.setMessage("Score:: ")
        tvScoreForm.animateTo(score, 200)
    }

    private fun startGame() {
        val data = DataSet.getStructure(unitNum)
        if (data == null) {
            goToNext()
            return
        }
        // all structure
        structures = data
        loadNextStructure()


    }

    private fun checkAnswer() {
        val userAnswer = edAnswer.text.toString().trim()
        if (userAnswer == currentStructureItem?.answer) {
            score += 1
            tvScoreForm.updateValue(Constants.SCORE_UNIT, 1000)
            SoundHelper.playCorrect(this)
        } else {
            SoundHelper.playFail(this)
        }
        currentStructureItemIndex++
        loadNextItem()
        edAnswer.setText("")

    }

    /**
     * load curent staructure
     */
    private fun loadNextStructure() {
        // check if there is still items
        if (currentStructureIndex < structures.size) {
            // save structure
            currentStructure = structures[currentStructureIndex]
            // place title
            stucureName.text = currentStructure?.name ?: ""
            // map it to items
            structureItems = structures[currentStructureIndex].toItems()
            // calculate total score of game
            gameTotalScore += structureItems.size
            loadNextItem()
        } else {
            // this case, all structure are finished
            // hide button as it appeare with finish fragment
            btnNext.visibility = View.INVISIBLE
            this.dismissKeyboard()
            FinshGame.showFinish(this, R.id.home, score, gameTotalScore, false)
        }
    }


    private fun loadNextItem() {
        if (currentStructureItemIndex < structureItems.size) {
            placeStructureItem()
        }
        else {
            // finished current structure examples
            currentStructureIndex += 1
            currentStructureItemIndex = 0
            loadNextStructure()
        }
    }

    private fun placeStructureItem() {
        // save it
        currentStructureItem = structureItems[currentStructureItemIndex]
        // place it
        strucureValue.text = currentStructureItem?.src ?: ""
    }

    override fun onStop() {
        super.onStop()
        saveScore(3)
    }
}
