package com.cs407_android.ormlab;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import de.greenrobot.daogenerator.*;
import de.greenrobot.*;

public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName, email, phone;
    Button submitButton;
    ListView listView;
    ArrayAdapter adapter;
    Context context;

    public static ArrayList<String> guestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate objects
        context = this;
        guestList = new ArrayList<>();

        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);

        submitButton = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.list_view);

        //set up ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, guestList);
        listView.setAdapter(adapter);

        //set up submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = firstName.getText().toString() + " " + lastName.getText().toString();

                Toast.makeText(context, "added " + name, Toast.LENGTH_SHORT).show();

                guestList.add(name);

                adapter.notifyDataSetChanged();


            }
        });

        //DevOpenHelper ex_database_helper_obj = new DaoMaster.DevOpenHelper(SharedVariables.globalContext, "ORM.sqlite", null);




    }
}
