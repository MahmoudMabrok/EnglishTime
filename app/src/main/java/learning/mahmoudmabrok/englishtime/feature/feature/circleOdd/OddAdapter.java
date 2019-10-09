package learning.mahmoudmabrok.englishtime.feature.feature.circleOdd;

import android.graphics.Color;
import android.os.CountDownTimer;
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
import learning.mahmoudmabrok.englishtime.feature.utils.Utils;

public class OddAdapter extends RecyclerView.Adapter<OddAdapter.Holder> {

    private List<String> list;
    private String odd;
    private OddFinish oddFinish;

    public OddAdapter() {
        list = new ArrayList<>();
    }

    public void setOddFinish(OddFinish oddFinish) {
        this.oddFinish = oddFinish;
    }

    public void setOddList(List<String> newList) {
        odd = newList.get(0);
        Collections.shuffle(newList);
        list = new ArrayList<>(newList);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.odd_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        String item = list.get(i);
        holder.mTvOddItem.setText(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (odd.equals(item)) {
                    holder.mTvOddItem.setBackgroundColor(Color.GREEN);
                    new CountDownTimer(500, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            oddFinish.onFinish();
                        }
                    }.start();
                } else {
                    holder.mTvOddItem.setBackgroundColor(Color.RED);
                    new CountDownTimer(500, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            notifyDataSetChanged();
                        }
                    }.start();
                    Utils.vibrate(holder.itemView.getContext());
                }
            }
        });
        // used to reset bg
        holder.mTvOddItem.setBackgroundResource(R.drawable.item_bg);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    interface OddFinish {
        void onFinish();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvOddItem)
        TextView mTvOddItem;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}