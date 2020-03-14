package learning.mahmoudmabrok.englishtime.feature.feature.selectPath

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_game_type.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.feature.categorizeWords.CategorizeWords
import learning.mahmoudmabrok.englishtime.feature.feature.categorizeWords.CategorizeWordsTwo
import learning.mahmoudmabrok.englishtime.feature.feature.completeWord.CompleteWord
import learning.mahmoudmabrok.englishtime.feature.feature.completeWord.CompleteWordTwo
import learning.mahmoudmabrok.englishtime.feature.utils.Constants

class SelectGameType : AppCompatActivity() {

    var type = ""
    var unit = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_game_type)

        type = intent.getStringExtra(Constants.TYPE)
        unit = intent.getIntExtra(Constants.UNIT, unit)


        btnOne.setOnClickListener {
            openOne()
        }

        btnTwo.setOnClickListener {
            openTwo()
        }

    }

    private fun openTwo() {
        val openAcivity = getDestin(2)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }

    private fun getDestin(mode: Int): Intent {
        return if (mode == 1)
            when (type) {
                Constants.CATEGORIES -> Intent(this, CategorizeWords::class.java)
                else -> Intent(this, CompleteWord::class.java)
            } else {
            when (type) {
                Constants.CATEGORIES -> Intent(this, CategorizeWordsTwo::class.java)
                else -> Intent(this, CompleteWordTwo::class.java)
            }
        }
    }

    private fun openOne() {
        val openAcivity = getDestin(1)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
    }
}
