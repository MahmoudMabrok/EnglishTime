package learning.mahmoudmabrok.englishtime.feature.feature.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import learning.mahmoudmabrok.englishtime.R;
import learning.mahmoudmabrok.englishtime.feature.feature.selectPath.SelectGameType;
import learning.mahmoudmabrok.englishtime.feature.utils.Constants;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {

    private List<HomeItem> list;

    public HomeAdapter() {
        list = new ArrayList<>();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public List<HomeItem> getList() {
        return list;
    }

    public void setList(List<HomeItem> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_item, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        HomeItem item = list.get(i);
        if (item.getScore() > 0)
            holder.mScore.setText(item.getScore() + "");

        holder.mTvUnitNO.setText(String.format(Locale.ENGLISH, "Unit %d", i + 1 + 5));
        holder.mTvUnitNO.setOnClickListener(e -> {
            Intent openAcivity = new Intent(holder.itemView.getContext(), SelectGameType.class);
            openAcivity.putExtra(Constants.UNIT, i);
            holder.itemView.getContext().startActivity(openAcivity);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView mTvUnitNO;
        TextView mScore;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mTvUnitNO = itemView.findViewById(R.id.tvUnitNO);
            mScore = itemView.findViewById(R.id.tvScore);
        }


    }

}