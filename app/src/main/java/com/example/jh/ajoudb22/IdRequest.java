package com.example.jh.ajoudb22;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class IdRequest extends StringRequest {
    final static private String URL = "http://jaejindb.cafe24.com/Find_id.php";
    private Map<String, String> parameters;

    public IdRequest(String userEmail, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userEmail", userEmail);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}