package com.example.sk173.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by sk173 on 2018-02-16.
 */

public class LoginActivity extends AppCompatActivity {

    String masterID ="master";
    String result;
    EditText idText,passwordText;
    Button loginBtn;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idText = findViewById(R.id.idLoginEdit);
        passwordText = findViewById(R.id.passLoginEdit);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final String userID = idText.getText().toString();
            final String userPassword= passwordText.getText().toString();
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                            /*
                            *   respones인터넷에서 건너오는 response를 받음
                            * */

                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        int userType = jsonResponse.getInt("type");
                        if(success){
                           final  String userID = jsonResponse.getString("userID");
                           final  String userPassword = jsonResponse.getString("userPassword");

                            masterID = userID;

                            Intent intent = new Intent(LoginActivity.this   , MainActivity.class);
                            intent.putExtra("userID",userID);
                           // intent.putExtra("userPassword",userPassword);
                            intent.putExtra("type", userType);
                           // intent.putExtra("result",result);
                            LoginActivity.this.startActivity(intent);

                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            //알림창이 registerSActivirty에 뜨게
                            builder.setMessage("로그인  실패 ")
                                    .setNegativeButton("다시시도",null)
                                    .create()
                                    .show();
                        }
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }


            };

            LoginRequest loginRequest = new LoginRequest(userID,userPassword,responseListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(loginRequest);//response를 받게되고 listener가 실행된다

        }
    });

    }


}
