package com.cs407_android.ormlab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName, email, phone;
    Button submitButton;
    ListView listView;
    ArrayAdapter adapter;
    Context context;

    public static ArrayList<String> guestList;

    //Uncomment once ready

    DaoMaster.DevOpenHelper guestBookDBHelper;
    SQLiteDatabase guestBookDB;
    DaoMaster daoMaster;
    DaoSession daoSession;
    GuestDao guestDao;
    List<Guest> guestListFromDB;

    boolean firstGuestGenerated = false;


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

        initDatabase();

        adapter.notifyDataSetChanged();


        //set up submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = firstName.getText().toString() + " " + lastName.getText().toString();

                Toast.makeText(context, "added " + name, Toast.LENGTH_SHORT).show();

                guestList.add(name);

                saveGuest();

                adapter.notifyDataSetChanged();


            }
        });


    }

    private void initDatabase()
    {
        guestBookDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        guestBookDB = guestBookDBHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(guestBookDB);

        //TODO: Create database and tables if non existent
        //Use methods in DaoMaster to create initial database table
        //

        //TODO: Create DaoSession instance
        //Use method in DaoMaster to create a database access session
        //daoSession =

        //TODO: From DaoSession instance, get instance of GuestDao
        //guestDao =

        //TODO: Get list of Guest objects in database using QueryBuilder
        //TODO: (cont.) If list is null, then database tables were created for first time in
        //TODO: (cont.) previous lines, so call "closeReopenDatabase()"
        //HINT: All instances of Guest objects will have their Display property set equal to true
        //guestListFromDB =

        //TODO: Add all Guest objects from List to guestList ArrayList and use
        //TODO: (cont.) "adapter.notifyDataSetChanged()" to update list

    }

    private void saveGuest()
    {
        //Generate random Id for Guest object to place in database
        Random rand = new Random();

        //TODO: Create Guest instance using data from MainActivity
        //TODO: (cont.) Use rand.nextLong() for Guest object Id
        //

        //TODO: Insert Guest instance into guestDao
        //

        //Close and reopen database to ensure Guest object is saved
        closeReopenDatabase();
    }

    private void closeDatabase()
    {
        daoSession.clear();
        guestBookDB.close();
        guestBookDBHelper.close();
    }

    private void closeReopenDatabase()
    {
        closeDatabase();

        guestBookDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        guestBookDB = guestBookDBHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(guestBookDB);

        //TODO: Create DaoSession instance
        //Use method in DaoMaster to create a database access session
        //daoSession =

        //TODO: From DaoSession instance, get instance of GuestDao
        //guestDao =
    }
}
