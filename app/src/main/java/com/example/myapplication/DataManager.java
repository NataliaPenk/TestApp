package com.example.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class DataManager {
    private FakeServer server = new FakeServer();

    private static volatile DataManager instance;

    static DataManager getInstance() {
        if (instance == null)
            synchronized (DataManager.class) {
                if (instance == null) {
                    instance = new DataManager();
                }
            }
        return instance;
    }

    private DataManager() {
    }

    private String itemToJSON(int id, String item) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("item", item);
        return obj.toString();
    }

    private String userCredentialsToJSON(String login, String password) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("login", login);
        obj.put("password", password);
        return obj.toString();
    }

    private String[] jsonToItem(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        JSONArray jsonData = obj.getJSONArray("data");
        String[] items = new String[jsonData.length()];
        for (int i = 0; i < items.length; ++i) {
            items[i] = jsonData.getString(i);
        }
        return items;
    }


    String[] fetchItems() {
        try {
            String json = server.getData();
            return jsonToItem(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new String[]{};
    }

    void saveItem(int index, String item) {
        try {
            String json = itemToJSON(index, item);
            server.setData(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    boolean authentificationAnswer(String login, String password) {
        try {
            String json = userCredentialsToJSON(login, password);
            return server.authentificationAnswer(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
