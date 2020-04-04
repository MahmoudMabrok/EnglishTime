package learning.mahmoudmabrok.englishtime.feature.feature.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nightonke.boommenu.BoomButtons.BoomButton
import com.nightonke.boommenu.BoomButtons.HamButton
import com.nightonke.boommenu.OnBoomListener
import kotlinx.android.synthetic.main.activity_main.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.feature.aorb.IsAOrB
import learning.mahmoudmabrok.englishtime.feature.feature.categorizeWords.CategorizeWords
import learning.mahmoudmabrok.englishtime.feature.feature.circleOdd.CircleOdd
import learning.mahmoudmabrok.englishtime.feature.feature.collectWordWithCross.CollectWord
import learning.mahmoudmabrok.englishtime.feature.feature.crossword.CrossWord
import learning.mahmoudmabrok.englishtime.feature.feature.grammer.GrammerActivity
import learning.mahmoudmabrok.englishtime.feature.feature.puncuate.Puncate
import learning.mahmoudmabrok.englishtime.feature.feature.selectPath.SelectGameType
import learning.mahmoudmabrok.englishtime.feature.feature.showVocabs.ShowVocabs
import learning.mahmoudmabrok.englishtime.feature.feature.snake.SnakeStair
import learning.mahmoudmabrok.englishtime.feature.utils.Constants

class Activities : AppCompatActivity() {


    internal var unit = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        unit = intent.getIntExtra(Constants.UNIT, 0)


        val names = arrayOf("Complete Miss", "Punctuate", "Grammar", "Structures", "Collect Words")
        for (i in 0 until bmb.piecePlaceEnum.pieceNumber()) {
            val builder = HamButton.Builder()
                    .normalText(names[i])
            bmb!!.addBuilder(builder)
        }

        bmb!!.onBoomListener = object : OnBoomListener {
            override fun onClicked(index: Int, boomButton: BoomButton) {
                when (index) {
                    0 -> completeWord()
                    1 -> puncate()
                    2 -> openGrammer()
                    3 -> openIsA()
                    4 -> openWords()
                    5 -> onViewClickedSnake()
                }
            }

            override fun onBackgroundClick() {

            }

            override fun onBoomWillHide() {

            }

            override fun onBoomDidHide() {

            }

            override fun onBoomWillShow() {

            }

            override fun onBoomDidShow() {

            }
        }
    }

    private fun openWords() {
        val openAcivity = Intent(this@Activities, SelectGameType::class.java)
        openAcivity.putExtra(Constants.TYPE, Constants.CATEGORIES)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }

    private fun openIsA() {
        val openAcivity = Intent(this@Activities, IsAOrB::class.java)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }

    private fun puncate() {
        val openAcivity = Intent(this@Activities, Puncate::class.java)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }

    private fun openGrammer() {
        val openAcivity = Intent(this@Activities, GrammerActivity::class.java)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }

    private fun completeWord() {
        val openAcivity = Intent(this@Activities, SelectGameType::class.java)
        openAcivity.putExtra(Constants.TYPE, Constants.WORDS)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        bmb!!.isAutoBoom = true
    }

    fun onButtonVocabClicked() {
        val openAcivity = Intent(this@Activities, ShowVocabs::class.java)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }

    fun onButtonFormClicked() {
        val openAcivity = Intent(this@Activities, CategorizeWords::class.java)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }

    fun onButtonOddClicked() {
        val openAcivity = Intent(this@Activities, CircleOdd::class.java)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }

    fun onButtonListenClicked() {
       /* val openAcivity = Intent(this@Activities, ListenAndSelect::class.java)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)*/
    }


    fun onViewClicked() {
        val openAcivity = Intent(this@Activities, CrossWord::class.java)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }


    fun onViewClickedSnake() {
        val openAcivity = Intent(this@Activities, SnakeStair::class.java)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }

    fun collectWords() {
        val openAcivity = Intent(this@Activities, CollectWord::class.java)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }


}
