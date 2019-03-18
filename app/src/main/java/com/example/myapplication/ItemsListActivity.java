package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;
import android.content.Intent;

public class ItemsListActivity extends AppCompatActivity {

    private ListView listView;
    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_list_activity);

        listView = findViewById(R.id.listView);

        configureListView();
//        fetchData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    private void configureListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ItemsListActivity.this, EditItemActivity.class); //Заполняем Intent
                int index = (int) id;
                String item = data[index];
                intent.putExtra("id", index); // прикрепляем к интенту передаваемые данные
                intent.putExtra("data", item); // прикрепляем к интенту передаваемые данные
                startActivity(intent); //Запускаем активность
            }
        });
    }

    private void fetchData() {
        DataManager dataManager = DataManager.getInstance();
        data = dataManager.fetchItems();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, data);

        listView.setAdapter(adapter);
    }
}