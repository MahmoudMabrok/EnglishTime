package learning.mahmoudmabrok.englishtime.feature.feature.selectPath

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_game_type.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.feature.activities.Activities
import learning.mahmoudmabrok.englishtime.feature.feature.current.aorb.IsAOrB
import learning.mahmoudmabrok.englishtime.feature.feature.current.categorizeWords.CategorizeWords
import learning.mahmoudmabrok.englishtime.feature.feature.current.categorizeWords.CategorizeWordsTwo
import learning.mahmoudmabrok.englishtime.feature.feature.current.completeWord.CompleteWord
import learning.mahmoudmabrok.englishtime.feature.feature.current.completeWord.CompleteWordTwo
import learning.mahmoudmabrok.englishtime.feature.feature.current.grammer.GrammerActivity
import learning.mahmoudmabrok.englishtime.feature.feature.current.puncuate.Puncate
import learning.mahmoudmabrok.englishtime.feature.utils.Constants

class SelectGameType : AppCompatActivity() {


    var unit = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_game_type)

        unit = intent.getIntExtra(Constants.UNIT, unit)


        btnOne.setOnClickListener {
            openOne()
        }

        btnTwo.setOnClickListener {
            openTwo()
        }

    }

    private fun openTwo() {
        val openAcivity = Intent(this, CompleteWordTwo::class.java)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
        finish()
    }

    private fun openOne() {
        val openAcivity = Intent(this, Activities::class.java)
        openAcivity.putExtra(Constants.UNIT, unit)
        startActivity(openAcivity)
        finish()
    }
}
