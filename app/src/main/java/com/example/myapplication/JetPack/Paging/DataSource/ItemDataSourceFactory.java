package com.example.myapplication.JetPack.Paging.DataSource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.myapplication.JetPack.Paging.Model.StackApiResponse;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, StackApiResponse.Item>> itemLiveDataSource = new MutableLiveData<>();

    @Override

    public DataSource<Integer, StackApiResponse.Item> create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveDataSource.postValue(itemDataSource);

        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, StackApiResponse.Item>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
