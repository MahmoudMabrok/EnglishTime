package learning.mahmoudmabrok.englishtime.feature.feature.snake;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;

public class SnakeStairAdapter extends RecyclerView.Adapter<SnakeStairAdapter.Holder> {

    private static final String TAG = "SnakeStairAdapter";
    private List<String> list;
    private List<Point> items;
    private int posOfCircle = -1;
    private List<Stair> stairs;
    private List<Snack> snackes;

    public SnakeStairAdapter() {
        list = new ArrayList<>();
        items = new ArrayList<>();
    }

    public void addString(String item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
        notifyItemRangeChanged(list.size() - 1, list.size());
    }


    public void setStringList(List<String> data) {
        list = new ArrayList<>(data);
        items = new ArrayList<>();

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
        Log.d(TAG, "onCreateViewHolder: " + i);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.snake_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.mTvSnake.setBackgroundResource(R.drawable.unit_bg);
        String item = String.valueOf((i + 1));
        holder.mTvSnake.setText(item);
        //  items.add(new Point(holder.itemView.getX(), holder.itemView.getY()));
        // Log.d(TAG, "onBindViewHolder: size $$ " + items.size());


        if (i == posOfCircle) {
            holder.mTvSnake.setVisibility(View.GONE);
            holder.mImSnake.setVisibility(View.VISIBLE);
        } else {
            holder.mTvSnake.setVisibility(View.VISIBLE);
            holder.mImSnake.setVisibility(View.GONE);
        }

        if (snackes != null && stairs != null) {

        }

    }

    public List<Point> getItems() {
        return items;
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return list.size();
    }

    public void setCircle(int pose) {
        posOfCircle = pose;
        notifyDataSetChanged();
    }

    public void logItems() {
        Log.d(TAG, "logItems: " + items.size());
        for (Point holder : items) {
            Log.d(TAG, "logItems: X$ " + holder.getX() + " , Y " + holder.getY());
        }
    }

    public List<Stair> getStairs() {
        return stairs;
    }

    public void setStairs(List<Stair> stairs) {
        this.stairs = stairs;
    }

    public List<Snack> getSnackes() {
        return snackes;
    }

    public void setSnackes(List<Snack> snackes) {
        this.snackes = snackes;
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvSnake)
        TextView mTvSnake;
        @BindView(R.id.imSnake)
        ImageView mImSnake;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}