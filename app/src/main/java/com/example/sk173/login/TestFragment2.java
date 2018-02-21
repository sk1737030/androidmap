package com.example.sk173.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sk173.login.people.PeopleFragment;

/**
 * Created by sk173 on 2018-02-21.
 */

public class TestFragment2 extends android.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        Log.e("들어옴","들이어ㅗㅅㄷ");
        View view = inflater.inflate(R.layout.test,container,false);


        return view;
    }

}
