package learning.mahmoudmabrok.englishtime.feature.feature.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import learning.mahmoudmabrok.englishtime.R;
import learning.mahmoudmabrok.englishtime.feature.feature.activities.Activities;
import learning.mahmoudmabrok.englishtime.feature.utils.Constants;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {

    private List<String> list;

    public HomeAdapter() {
        list = new ArrayList<>();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_item, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        holder.mTvUnitNO.setText(String.format(Locale.ENGLISH, "Unit %d", i + 1 + 5));
        holder.mTvUnitNO.setOnClickListener(e -> {
            {
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0F, 0.5F, 1.0F, 0.5F);
                scaleAnimation.setDuration(800);
                holder.mTvUnitNO.startAnimation(scaleAnimation);
                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent openAcivity = new Intent(holder.itemView.getContext(), Activities.class);
                        openAcivity.putExtra(Constants.UNIT, i);
                        holder.itemView.getContext().startActivity(openAcivity);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }

        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView mTvUnitNO;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mTvUnitNO = itemView.findViewById(R.id.tvUnitNO);
        }
    }

}