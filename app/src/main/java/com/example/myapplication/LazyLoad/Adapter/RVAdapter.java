package com.example.myapplication.LazyLoad.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.LazyLoad.Inteface.ILoadMore;
import com.example.myapplication.LazyLoad.Model.Item;
import com.example.myapplication.R;

import java.util.List;


public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    private ILoadMore iLoadMore;
    private boolean isLoading;
    private Context context;
    private List<Item> list;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public RVAdapter(RecyclerView recyclerView, Context context, List<Item> list) {
        this.context = context;
        this.list = list;
        recyclerViewAddOnScrollListener(recyclerView);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
            return new RVViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
            return new RVLoading(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RVViewHolder) {
            Item item = list.get(position);
            RVViewHolder viewHolder = (RVViewHolder) holder;

            viewHolder.txtName.setText(item.getName());
            viewHolder.txtLength.setText(String.valueOf(item.getLength()));
        } else if (holder instanceof RVLoading) {
            RVLoading rvLoading = (RVLoading) holder;
            rvLoading.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setiLoadMore(ILoadMore iLoadMore) {
        this.iLoadMore = iLoadMore;
    }

    public void setLoaded() {
        isLoading = false;
    }


    private void recyclerViewAddOnScrollListener(RecyclerView recyclerView) {
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (linearLayoutManager != null) {
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                }
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (iLoadMore != null)
                        iLoadMore.onLoadMore();
                    isLoading = true;
                }
            }
        });
    }


    private class RVViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtLength;

        public RVViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtLength = itemView.findViewById(R.id.txtLength);
        }
    }

    private class RVLoading extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        public RVLoading(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

    }
}
