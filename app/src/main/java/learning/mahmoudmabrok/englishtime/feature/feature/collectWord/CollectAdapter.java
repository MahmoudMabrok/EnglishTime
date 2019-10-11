package learning.mahmoudmabrok.englishtime.feature.feature.collectWord;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.Holder> {

    private static final String TAG = "CollectAdapter";

    private List<Character> chars;
    private List<String> words;
    private List<String> downWords = new ArrayList<>();
    private List<Integer> selectedIndexes = new ArrayList<>();
    private List<Integer> doneIndexes = new ArrayList<>();


    public CollectAdapter(CollectGame collectGame) {
        chars = collectGame.getChars();
        words = collectGame.getWords();
    }


    public void clear() {
        selectedIndexes.clear();
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.collect_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int pos) {
        Character item = chars.get(pos);
        holder.mBtnCollect.setText(item.toString());

        if (doneIndexes.contains(pos)) {
            holder.itemView.setBackgroundResource(R.drawable.collect_done);
        } else if (selectedIndexes.contains(pos)) {
            holder.itemView.setBackgroundResource(R.drawable.collect_selected);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.collect_unselected);
        }

    }

    public List<String> getWords() {
        return words;
    }

    @Override
    public int getItemCount() {
        return chars.size();
    }

    private boolean isAllowedToClick(int pos) {
        // first item to select
        if (selectedIndexes.isEmpty())
            return true;
        else {
            // get check same row or col
            return isInSameRowOrCol(selectedIndexes, pos);
        }
    }

    private boolean isInSameRowOrCol(List<Integer> selectedIndexes, int pos) {
        for (int last : selectedIndexes) {
            if (last % 5 != pos % 5 && last / 5 != pos / 5)
                return false;
        }
        return true;
    }

    private void check() {
        String word = getWordFromSelected();
        Log.d(TAG, "check: " + word);
        for (String aWord : words
        ) {
            if (aWord.equals(word)) {
                addSelectedToDone(word);
                break;
            }
        }
    }

    private void addSelectedToDone(String word) {
        downWords.add(word);
        doneIndexes.addAll(selectedIndexes);
        selectedIndexes.clear();
        notifyDataSetChanged();
    }

    private String getWordFromSelected() {
        StringBuilder builder = new StringBuilder();
        Collections.sort(selectedIndexes);
        for (int i : selectedIndexes
        ) {
            builder.append(chars.get(i));
        }
        return builder.toString();
    }

    interface KeybordListner {
        void keyClicked(Character c);
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvCollect)
        TextView mBtnCollect;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Log.d(TAG, "onClick: " + pos);
            if (selectedIndexes.contains(pos)) {
                // clicked before
                selectedIndexes.remove(Integer.valueOf(pos));
                v.setBackgroundResource(R.drawable.collect_unselected);
            } else {
                // here check first its allowed to click here
                // rule that we select items in same row and col
                if (isAllowedToClick(pos) && !doneIndexes.contains(pos)) {
                    selectedIndexes.add(pos);
                    v.setBackgroundResource(R.drawable.collect_selected);
                } else {
                    // as it wrong click so clear screen
                    clear();
                }
            }
            check();
        }
    }


}