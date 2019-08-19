package com.example.myapplication.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemData {

    @SerializedName("status")
    private String status;

    @SerializedName("result")
    private List<ItemResult> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemResult> getResult() {
        return result;
    }

    public void setResult(List<ItemResult> result) {
        this.result = result;
    }
}
