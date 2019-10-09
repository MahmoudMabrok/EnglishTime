package learning.mahmoudmabrok.englishtime.feature.feature.crossword;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;

public class CrossAdapter extends RecyclerView.Adapter<CrossAdapter.Holder> {

    private static final String TAG = "CrossAdapter";
    private Character[] correctArr;
    private List<Integer> listNA = new ArrayList<>();
    private Character[] myList;
    private List<Integer> myHints;
    private CrossListener crossListener;
    private int clicked = -1;

    CrossAdapter(Character[] make, int size, List<Integer> hints) {
        correctArr = new Character[size];
        myList = new Character[size];
        for (int i = 0; i < myList.length; i++) {
            if (make[i] == '#') {
                myList[i] = '#';
                listNA.add(i);
            } else
                myList[i] = ' ';

            correctArr[i] = make[i];
        }
        myHints = new ArrayList<>(hints);
        // log(myList);
    }

    public static void log(Character[] ll) {
        int n = 0;
        for (char c : ll) {
            Log.d(TAG, (n++) + " log: " + c);
        }
    }

    void setCrossListener(CrossListener crossListener) {
        this.crossListener = crossListener;
    }

    public Character[] getList() {
        return myList;
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cross_item, viewGroup, false);
        //    Log.d(TAG, "onCreateViewHolder: "+i);
        return new Holder(view);
    }

    public void setChar(int pos, char c) {
        myList[pos] = c;
        notifyItemChanged(pos);
        notifyItemRangeChanged(pos, 1);
        //   log(myList);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.tvCrossHint.setText("");
        // default style
        if (i == clicked) {
            Log.d(TAG, "onBindViewHolder: i == clicked   " + i);
            holder.itemView.setBackgroundResource(R.drawable.cross_selected);
            clicked = -1;
        } else {
            holder.itemView.setBackgroundResource(R.drawable.cross_unselected);
        }

        char item = myList[i];
        Log.d(TAG, "onBindViewHolder: item " + i + " $$ " + item);
        if (item == '#') {
            //    Log.d(TAG, "onBindViewHolder: ## " + i );
            holder.itemView.setBackgroundResource(R.drawable.cross_na);
            holder.itemView.setClickable(false);
            holder.itemView.setEnabled(false);
        } else {
            if (!listNA.contains(i)) {
                holder.mTvCross.setText(String.valueOf(item));
                holder.itemView.setClickable(true);
                holder.itemView.setEnabled(true);

                if (item == correctArr[i]) {
                    holder.itemView.setBackgroundResource(R.drawable.cross_correct);
                }
            }
        }
        // hints
        if (myHints.contains(i)) {
            //     Log.d(TAG, "onBindViewHolder: myHints " + i );
            holder.tvCrossHint.setText(String.valueOf(myHints.indexOf(i) + 1));
        }

        // checkFull
        if (Arrays.deepEquals(myList, correctArr)) {
            crossListener.onCrossFinished();
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return myList.length;
    }

    interface CrossListener {
        void onCrossClicked(int pos);

        void onCrossFinished();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCross)
        TextView mTvCross;

        @BindView(R.id.tvCrossHint)
        TextView tvCrossHint;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //          Log.d(TAG, "onClick: $$ " + getAdapterPosition());
                    log(myList);
                    if (!listNA.contains(getAdapterPosition())) {
                        int pos = getAdapterPosition();
                        v.setBackgroundResource(R.drawable.cross_selected);
                        crossListener.onCrossClicked(pos);
                        clicked = pos;
                        notifyDataSetChanged();
                        Log.d(TAG, "onClick: pos " + pos);
                    } else {
                        Log.d(TAG, "onClick: NA" + getAdapterPosition());
                    }
                }
            });
        }


    }


}