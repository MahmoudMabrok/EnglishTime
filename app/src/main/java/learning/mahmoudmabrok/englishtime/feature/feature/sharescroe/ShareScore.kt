package learning.mahmoudmabrok.englishtime.feature.feature.sharescroe


import android.content.Intent
import android.graphics.Bitmap.CompressFormat
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_share_score.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.utils.log
import java.io.File
import java.io.FileOutputStream


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

            try {
                val file = File(getCacheDir(), name.toString() + ".png")
                val fOut = FileOutputStream(file)
                "onCreate aa".log("")
                bitmap.compress(CompressFormat.PNG, 100, fOut)
                fOut.flush()
                fOut.close()
                file.setReadable(true, false)
                val intent = Intent(Intent.ACTION_SEND)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
                intent.type = "image/png"
                startActivity(intent)
                "onCreate aaaa".log("")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            layoutH.isDrawingCacheEnabled = false
        }

    }
}
