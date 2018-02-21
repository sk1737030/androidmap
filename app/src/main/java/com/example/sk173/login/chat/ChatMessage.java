package com.example.sk173.login.chat;


import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sk173.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChatMessage extends AppCompatActivity {

        private List<Item> itemList = new ArrayList<>();
        private RecyclerView recyclerView;
        private ChatMessageAdapter mAdapter;
        private EditText message_editText;
        private Button message_button;
        private TextView activity_item_comments;
        private TextView activity_item_userId;
        String type ="3";
        private String userID ;
        boolean judgementBoolean= true; //전송 버튼눌럿을때에 다시 부르기위한 판단
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.e("실행1", "실행");
            setContentView(R.layout.activity_chat_message);
            Bundle extras = getIntent().getExtras();
            userID = extras.getString("userID");

            CustomTask task= new CustomTask();
            task.execute(type);

            recyclerView =  findViewById(R.id.messageActivity_recyclerview);

            mAdapter = new ChatMessageAdapter(itemList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

            message_editText = findViewById(R.id.activity_chat_message_editText);
            message_button = findViewById(R.id.activity_chat_message_button);
            activity_item_comments = findViewById(R.id.activity_item_comment);
            activity_item_userId = findViewById(R.id.activity_item_userId);




            message_button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if (message_editText.getText().toString() != null && !message_editText.getText().toString().equals("")) {
                         type = "4";
                        String destinationUid = "1234";
                        String comments = message_editText.getText().toString();
                        CustomTask task = new CustomTask();
                        task.execute(userID, comments, destinationUid, type);
                        message_editText.setText("");//clear
                    }
                }
            });

        }

        private void prepareMovieData(String chat_userId, String chat_comments,String time) {

            Item item = new Item(chat_userId, chat_comments);
            itemList.add(item);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter.notifyDataSetChanged();
                }
            });

    }
    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        BufferedInputStream buf ;
        @Override
        // doInBackground의 매개값이 문자열 배열인데요. 보낼 값이 여러개일 경우를 위해 배열로 합니다.
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://sk173703.cafe24.com/saveMap3/data.jsp");//보낼 jsp 주소
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");//데이터를 POST 방식으로 전송합니다.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());


                sendMsg = "type=" + strings[0];// "id=rain483&pwd=1234" 타입을 주어서 구별;
                if(type=="4"){
                    sendMsg = "userID= " + strings[0] + "&comments=" + strings[1]+"&destinationId="+strings[2]+"&type=" + strings[3];// "id=rain483&pwd=1234" 타입을 주어서 구별;
                    judgementBoolean =false;
                }

                //회원가입처럼 보낼 데이터가 여러 개일 경우 &로 구분하여 작성합니다.
                osw.write(sendMsg);//OutputStreamWriter에 담아 전송합니다.
                osw.flush();
                ////post 처리방식
                if (judgementBoolean==false)
                {
                    clear();
                }
                buf  = new BufferedInputStream(conn.getInputStream());
                // 데이터를 버퍼에 기록
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));

                String line  = null;
                String page  = "";

                // 버퍼의 웹문서 소스를 줄 단위로 읽어(line), page에 저장함
                while ((line = bufreader.readLine()) != null) {
                    page += line;
                }

                // 읽어들인 JSON 포맷의 데이어틀 JSON 객체로 변환
                JSONObject json = new JSONObject(page);
                // customers에 해당하는 배열을 할당
                JSONArray jArr = json.getJSONArray("univ");

                // 배열의 크기만큼 반복하면서, name과 address의 값을 추출함
                for (int i=0; i<jArr.length(); i++) {

                    // i번째 배열 할당(
                    json = jArr.getJSONObject(i);

                    // name과 address의 값을 추출함
                    String userId   = json.getString("userID");
                    String comments = json.getString("comments");
                    String time = json.getString("time");
                    Log.e("tager","comments"+comments);
                    prepareMovieData(userId,comments,time);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            //jsp로부터 받은 리턴 값입니다.
            return receiveMsg;
        }
    }
    public void clear(){itemList.clear();}
}