package learning.mahmoudmabrok.englishtime.feature.feature.crossword;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;

public class KeyboardAdapter extends RecyclerView.Adapter<KeyboardAdapter.Holder> {

    private List<Character> list;
    private KeybordListner keybordListner;

    public KeyboardAdapter() {
        list = new ArrayList<>();
    }

    public void setKeybordListner(KeybordListner keybordListner) {
        this.keybordListner = keybordListner;
    }

    public void addCharacter(Character item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
        notifyItemRangeChanged(list.size() - 1, list.size());
    }


    public void setCharacterList(List<Character> data) {
        for (Character item : data)
            addCharacter(item);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public List<Character> getList() {
        return list;
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.key_item, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Character item = list.get(i);
        holder.mBtnkey.setText(item.toString());

        holder.mBtnkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keybordListner.keyClicked(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    interface KeybordListner {
        void keyClicked(Character c);
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.btnkey)
        Button mBtnkey;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}