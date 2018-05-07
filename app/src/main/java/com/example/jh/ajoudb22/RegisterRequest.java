package com.example.jh.ajoudb22;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://jaejindb.cafe24.com/Register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userEmail, String userSchool,String userMajor, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userEmail", userEmail);
        parameters.put("userSchool", userSchool);
        parameters.put("userMajor", userMajor);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}