package com.example.sk173.login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sk173 on 2018-02-16.
 */

public class LoginRequest extends StringRequest  {

    final private static String URL = "http://sk1737030.cafe24.com/parcelTest/Login.php";
    private Map<String,String> parameters;

    public LoginRequest(String userID, String userPassword,Response.Listener<String> listener){
        //전송부분
        super(Method.POST , URL, listener,null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
    }
    //get부분
    @Override
    public Map<String,String> getParams(){
        return parameters;
    }


}
