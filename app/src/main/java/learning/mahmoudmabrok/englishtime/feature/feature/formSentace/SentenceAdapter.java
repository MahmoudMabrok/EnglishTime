package learning.mahmoudmabrok.englishtime.feature.feature.formSentace;

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

public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.Holder> {

    private List<String> list;
    private SentenceListener sentenceListener;

    public SentenceAdapter() {
        list = new ArrayList<>();
    }

    public void setSentenceListener(SentenceListener sentenceListener) {
        this.sentenceListener = sentenceListener;
    }

    public List<String> getList() {
        return list;
    }

    public void addSentence(String item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
        notifyItemRangeChanged(list.size() - 1, 1);
    }

    public void removeSentence(int pos) {
        list.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos, list.size());
    }


    public void setSentenceList(List<String> newList) {
        Collections.shuffle(newList);
        list = new ArrayList<>();
        for (String item : newList)
            addSentence(item);
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public String getFullSentence() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i));
            if (i != list.size() - 1)
                builder.append(" ");
        }
        return builder.toString();
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sentence_item, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        String item = list.get(i);
        holder.mTvWordItem.setText(item);

        holder.itemView.setOnClickListener(e -> {
            // first remove it form list here
            sentenceListener.onSentenceClicked(i, item);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    interface SentenceListener {
        void onSentenceClicked(int pos, String item);
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvWordItem)
        TextView mTvWordItem;


        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}