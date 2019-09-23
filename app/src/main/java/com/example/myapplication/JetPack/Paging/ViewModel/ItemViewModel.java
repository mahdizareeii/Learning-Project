package com.example.myapplication.JetPack.Paging.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.myapplication.JetPack.Paging.DataSource.ItemDataSource;
import com.example.myapplication.JetPack.Paging.DataSource.ItemDataSourceFactory;
import com.example.myapplication.JetPack.Paging.Model.StackApiResponse;

public class ItemViewModel extends ViewModel {
    public LiveData<PagedList<StackApiResponse.Item>> itemPagedList;
    public LiveData<PageKeyedDataSource<Integer, StackApiResponse.Item>> liveDataSource;

    public ItemViewModel() {
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false).setPageSize(ItemDataSource.PAGE_SIZE).build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)).build();
    }
}
