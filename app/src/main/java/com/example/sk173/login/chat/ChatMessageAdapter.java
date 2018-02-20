package com.example.sk173.login.chat;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;



import com.example.sk173.login.R;


import java.util.List;

/**
 * Created by sk173 on 2018-02-20.
 */

public class ChatMessageAdapter  extends RecyclerView.Adapter<ChatMessageAdapter.MyViewHolder>  {

    private List<Item> itemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  chat_userId;
        public TextView chat_comments;
        public MyViewHolder(View view) {
            super(view);
            chat_comments =   view.findViewById(R.id.activity_item_comment);
            chat_userId =  view.findViewById(R.id.activity_item_userId);
        }
    }


    public ChatMessageAdapter(List<Item> moviesList) {
        this.itemList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item, parent, false);
                //inflate에서 아이템 layout을 적용
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.chat_comments.setText(item.getChat_comments());
        holder.chat_userId.setText(item.getChat_userId());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
