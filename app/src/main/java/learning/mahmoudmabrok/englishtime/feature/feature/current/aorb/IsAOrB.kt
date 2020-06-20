package learning.mahmoudmabrok.englishtime.feature.feature.current.aorb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_is_aor_b_alt.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Structure
import learning.mahmoudmabrok.englishtime.feature.feature.collectWordWithCross.CollectWord
import learning.mahmoudmabrok.englishtime.feature.feature.current.categorizeWords.CategorizeWords
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.FinshGame
import learning.mahmoudmabrok.englishtime.feature.utils.openActivity
import learning.mahmoudmabrok.englishtime.feature.utils.show


class IsAOrB : BasicActivity() {

    var currentGrammer = 0
    var currentGrammerItem = 0

    lateinit var structures: List<Structure>
    lateinit var currentLessoen: String
    lateinit var listItems: List<String>

    /**
     * this will be called after finish
     */
    override fun goToNext() {
        openActivity(CategorizeWords::class.java) {
            putInt(Constants.UNIT, unitNum)
            putInt(Constants.SCORE_KEY, 0 + prevScore)
        }
        // so no back
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_is_aor_b_alt)

        unitNum = intent.getIntExtra(Constants.UNIT, 0)
        startGame()
    }

    private fun startGame() {

        val data = DataSet.getStructure(unitNum)

        if (data == null) {
            this.show("No Exercise here")
            Thread {
                Thread.sleep(500)
                runOnUiThread {
                    goToNext()
                }
            }.start()
        } else {
            structures = data
            loadLesson()

            btnNext.setOnClickListener {
                currentGrammerItem += 1
                loadNextItem()
            }

        }
/*        btnISA.setOnClickListener {
            checkAnswer(0)
        }

        btnIsB.setOnClickListener {
            checkAnswer(1)
        }

        play()

        configeButtons()*/
    }

    private fun loadLesson() {
        if (currentGrammer < structures.size) {
            stucureName.text = structures[currentGrammer].name
            listItems = structures[currentGrammer].getItems()
            loadNextItem()
        } else {
            FinshGame.showFinish(this, R.id.home, 0)
        }
    }

    override fun retryGame() {}


    private fun loadNextItem() {
        if (currentGrammerItem < listItems.size)
            strucureValue.text = listItems[currentGrammerItem]
        else {
            currentGrammer += 1
            currentGrammerItem = 0
            loadLesson()
        }
    }


    /*private fun checkAnswer(i: Int) {
        if (data.answers[current] == i){
            this.show("Right")
        }else{
            this.show("Wrong")
        }
        current += 1

        if (current < data.values.size){
            play()
        }else{
            finish()
        }

    }

    private fun configeButtons() {
        btnISA.text = data.types[0]
        btnIsB.text = data.types[1]
    }

    private fun play() {
        tvIsASource.text = data.values[current]
    }*/
}
