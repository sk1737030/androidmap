package com.example.sk173.login.people;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sk173.login.R;
import com.example.sk173.login.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk173 on 2018-02-21.
 */

public class PeopleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_people,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.peoplefragment_RecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        recyclerView.setAdapter(new PeopleFragmentRecyclerViewAdapter());

        return view;
    }

    private class PeopleFragmentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<UserModel> userModels;


            //수정
        public PeopleFragmentRecyclerViewAdapter() {
          /*  userModels = new ArrayList<>();
            final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            FirebaseDatabase.getInstance().getReference().child("users").addValueEventListener(new ValueEventListener() {
                //서버에서 넘어오는 데이터부분
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userModels.clear();//중복된것을 없애줌 한 번만 불러오게 없으면 계속 쌓임
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        //usermodel에 쌓임
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        //자신의 아이디표시를 제거하는 코드 중복 없애기 만약 내아이디가있으면 넘김
                        if(userModel.uid.equals(myUid)){
                            continue;
                        }
                        userModels.add(userModel);
                    }
                    //새로고침부분
                    notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });*/
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend,parent,false);

            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
        private class CustomViewHolder extends RecyclerView.ViewHolder{

            public TextView textView;

            public CustomViewHolder(View view){
                super(view);

                textView = view.findViewById(R.id.frienditem_textView);

            }
        }
    }
}
