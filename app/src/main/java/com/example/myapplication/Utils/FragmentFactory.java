package com.example.myapplication.Utils;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentFactory {

    private AppCompatActivity appCompatActivity;
    private int container;

    //Fragments
    public FragmentFactory(AppCompatActivity appCompatActivity, @IdRes int container) {
        this.appCompatActivity = appCompatActivity;
        if (container == 0)
            this.container = android.R.id.content;
        else
            this.container = container;
    }

    public enum callType {
        add,
        replace
    }

    public enum FragmentName {
        FRAGMENT_ONE,
        FRAGMENT_TWO,
        FRAGMENT_THREE
    }

    private Fragment getFragment(FragmentName fragmentName) {
        switch (fragmentName) {
            case FRAGMENT_ONE:
                /*if (fragmentOne == null)
                    fragmentOne = new FragmentOne();
                return fragmentOne;*/


        }
        return null;
    }

    public Fragment setFragment(FragmentName fragmentName, Bundle param, callType callType, boolean addToStack) {
        Fragment fragment = getFragment(fragmentName);
        if (fragment != null) {
            fragment.setArguments(param);
        }
        fragmentTransactionType(callType, fragment, fragmentName, addToStack);
        return fragment;
    }

    private void fragmentTransactionType(callType callType, Fragment fragment, FragmentName fragmentName, boolean addToStack) {
        switch (callType) {
            case add:
                fragmentAdd(fragment, fragmentName, addToStack);
                break;

            case replace:
                fragmentReplace(fragment, fragmentName, addToStack);
                break;
        }
    }

    private void fragmentAdd(Fragment fragment, FragmentName fragmentName, boolean addToStack) {
        FragmentTransaction fragmentTransaction = appCompatActivity.getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .add(container, fragment);

        if (addToStack)
            fragmentTransaction.addToBackStack(fragmentName.toString());

        fragmentTransaction.commit();
    }

    private void fragmentReplace(Fragment fragment, FragmentName fragmentName, boolean addToStack) {
        FragmentTransaction fragmentTransaction = appCompatActivity.getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(container, fragment);

        if (addToStack)
            fragmentTransaction.addToBackStack(fragmentName.toString());

        fragmentTransaction.commit();
    }

}
