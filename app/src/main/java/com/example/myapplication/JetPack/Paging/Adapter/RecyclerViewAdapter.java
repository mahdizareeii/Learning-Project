package com.example.myapplication.JetPack.Paging.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.JetPack.Paging.Model.StackApiResponse;
import com.example.myapplication.R;

public class RecyclerViewAdapter extends PagedListAdapter<StackApiResponse.Item, RecyclerViewAdapter.ViewHolder> {

    private Context context;

    public RecyclerViewAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StackApiResponse.Item item = getItem(position);

        if (item != null) {
            holder.textView.setText(item.owner.display_name);
            Glide.with(context).load(item.owner.profile_image).into(holder.imageView);
        } else {
            Toast.makeText(context, "item is null", Toast.LENGTH_SHORT).show();
        }
    }

    private static DiffUtil.ItemCallback<StackApiResponse.Item> DIFF_CALLBACK = new DiffUtil.ItemCallback<StackApiResponse.Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull StackApiResponse.Item oldItem, @NonNull StackApiResponse.Item newItem) {
            return oldItem.question_id == newItem.question_id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull StackApiResponse.Item oldItem, @NonNull StackApiResponse.Item newItem) {
            return oldItem.equals(newItem);
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtRecyclerView);
            imageView = itemView.findViewById(R.id.imgRecyclerView);
        }
    }
}
