package com.example.sk173.login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sk173 on 2018-02-16.
 */

public class RegisterRequest extends StringRequest  {

    final private static String URL = "http://sk173703.cafe24.com/parcel/Register.php";
    private Map<String,String> parameters;

    public RegisterRequest(String userID, String userPassword, String userName, int userAge , Response.Listener<String> listener){
        //전송부분
    super(Method.POST , URL, listener,null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
        parameters.put("userName",userName);
        parameters.put("userAge",userAge+""); //userAge는 int기떄문에 뒤에 공백추가하여 문자열로만듬
    }
    //get부분
    @Override
    public Map<String,String> getParams(){
        return parameters;
    }


}
