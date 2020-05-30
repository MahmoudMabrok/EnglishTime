package learning.mahmoudmabrok.englishtime.feature.feature.sharescroe


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_share_score.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.utils.log


class ShareScore : AppCompatActivity() {

    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_score)


        val localDB = LocalDB.getINSTANCE(this)
        score = localDB.score


        btnCertiificate.setOnClickListener {
            val name = when (edName.text.toString().isBlank()) {
                true -> "Ahmed"
                else -> edName.text.toString()
            }

            tvMessage.text = getString(R.string.final_name, name, score, 114)

            layoutH.isDrawingCacheEnabled = true
            val bitmap = layoutH.drawingCache
            layoutH.isDrawingCacheEnabled = false

            val pathofBmp: String = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "title", null)
            "onCreate  $pathofBmp".log("aaa")
            val bmpUri: Uri = Uri.parse(pathofBmp)
            val emailIntent1 = Intent(Intent.ACTION_SEND)
            emailIntent1.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            emailIntent1.putExtra(Intent.EXTRA_STREAM, bmpUri)
            emailIntent1.type = "image/png"

        }
    }
}
