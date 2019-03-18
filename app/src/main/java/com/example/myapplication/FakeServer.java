package com.example.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

class FakeServer {
    private String[] data;

    FakeServer () {
        generateData();
    }

    private void generateData() {
        data = new String[200];
        for (int i = 0; i < 200; ++i) {
            data[i] = randomString();
        }
    }

    private String randomString() {
        int length = 8;
        Random generator = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            //get random char from 'a' to 'z'
            char letter = (char)(generator.nextInt(26) + 97);
            result.append(letter);
        }
        return result.toString();
    }

    // return { data: [String] }
    String getData() throws JSONException {
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray(data);
        obj.put("data", array);
        return obj.toString();
    }
    // { "id": Int, "item": String }
    void setData(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        int id = obj.getInt("id");
        String item = obj.getString("item");
        data[id] = item;
    }

    Boolean authentificationAnswer(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        String validPassword = "vp";
        String validLogin = "vl";
        String login = obj.getString("login");
        String password = obj.getString("password");
        return login.equals(validLogin) && password.equals(validPassword);
    }

}
