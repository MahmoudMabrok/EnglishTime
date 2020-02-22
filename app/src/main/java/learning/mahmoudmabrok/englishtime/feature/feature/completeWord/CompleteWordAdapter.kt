package learning.mahmoudmabrok.englishtime.feature.feature.completeWord

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.utils.dismissKeyboard

/**
 * @property data char that displayed
 * @property count number ot missed char
 */
class CompleteWordAdapter(val data: MutableList<Char>, val count: Int) : RecyclerView.Adapter<CompleteWordAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.complete_word_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(splitedData: MutableList<Char>) {
        data.clear()
        data.addAll(splitedData)
        notifyItemRangeChanged(0, data.size)
    }

    inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(newItem: Char) {
            edText.clearFocus()
            if (newItem != ' ') {
                edText.setText(newItem.toString())
                edText.isEnabled = false
            } else {
                edText.isEnabled = true
                edText.text = null
                edText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable) {
                        itemView.context.dismissKeyboard()
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    /**
                     * @param s hold all current text
                     * @param start
                     * @param before
                     * @param count count of chars changed
                     */
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, countOfChange: Int) {
                        val pos = adapterPosition
                        val count = s.length
                        when {
                            count == 0 -> data[pos] = ' '
                            count == 1 -> {
                                data[pos] = s.first()
                            }
                            count > 1 ->
                                // make edit contains only one char
                                edText.setText(s.first().toString())
                        }
                    }
                })
            }
        }

        val edText: EditText = item.findViewById(R.id.edCompleteWord)

    }
}