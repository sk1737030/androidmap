package com.example.sk173.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText idText = findViewById(R.id.idText);
        final EditText passwordText = findViewById(R.id.passwordText);
        final EditText nameEdit = findViewById(R.id.nameEdit);
        final EditText ageEdit = findViewById(R.id.ageEdit);
        Button registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String userID = idText.getText().toString();
                String userPassword= passwordText.getText().toString();
                String userName = nameEdit.getText().toString();
                int userAge = Integer.parseInt(ageEdit.getText().toString());

                //버튼을 클릭했을때 특정한 요청을 response 한 이후에 listener에서 원하는 결과값을 다루게한다
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                        public void onResponse(String response)
                        {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                //알림창이 registerSActivirty에 뜨게
                                builder.setMessage("회원 등록 성공 ")
                                        .setPositiveButton("확인",null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(RegisterActivity.this , LoginActivity.class);
                                //register에서 login으로 넘어가는 설정
                                RegisterActivity.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                //알림창이 registerSActivirty에 뜨게
                                builder.setMessage("회원 등록 실패 ")
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

                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword,userName,userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);//response를 받게되고 listener가 실행된다

            }
        });
    }
}
