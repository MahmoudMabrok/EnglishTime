package learning.mahmoudmabrok.englishtime.feature.feature.showVocabs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;
import learning.mahmoudmabrok.englishtime.feature.models.Vocab;

public class VocabAdapter extends RecyclerView.Adapter<VocabAdapter.Holder> {

    private List<Vocab> list;
    private VocabListener vocabListener;

    public VocabAdapter() {
        list = new ArrayList<>();
    }

    public void setVocabListener(VocabListener vocabListener) {
        this.vocabListener = vocabListener;
    }

    public void addVocab(Vocab item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
        notifyItemRangeChanged(list.size() - 1, list.size());
    }

    public void setVocabList(List<Vocab> newList) {
        list = new ArrayList<>();
        for (Vocab vocab : newList)
            addVocab(vocab);
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vocab_item, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Vocab item = list.get(i);
        holder.mTvVocabAr.setText(item.getArVocab());
        holder.mTvVocabEn.setText(item.getEnVocab());
        holder.itemView.setOnClickListener(e -> vocabListener.onVocabClicked(item));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    interface VocabListener {
        void onVocabClicked(Vocab vocab);
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvVocabEn)
        TextView mTvVocabEn;
        @BindView(R.id.tvVocabAr)
        TextView mTvVocabAr;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}