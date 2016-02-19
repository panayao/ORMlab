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
                closeReopenDatabase();

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

        //TODO: Create DaoSession instance
        //Use method in DaoMaster to create a database access session

        //TODO: From DaoSession instance, get instance of GuestDao

        //TODO: Get list of Guest objects in database using QueryBuilder
        //HINT: All instances of Guest objects will have their Display property set equal to true
        if (guestDao.queryBuilder().where(
            GuestDao.Properties.Display.eq(true)).list() == null)
        {
            closeReopenDatabase();
        }
        guestListFromDB = guestDao.queryBuilder().where(
                GuestDao.Properties.Display.eq(true)).list();

        if (guestListFromDB != null) {

            for (Guest guest : guestListFromDB)
            {
                if (guest == null)
                {
                    return;
                }
                Toast.makeText(context, "Added Guests from Database", Toast.LENGTH_SHORT).show();
                guestList.add(guest.getFirstName() + " " + guest.getLastName());
            }
            adapter.notifyDataSetChanged();
        }
    }

    private void saveGuest()
    {
        Random rand = new Random();
        Guest newGuest = new Guest(rand.nextLong(), firstName.getText().toString(),
            lastName.getText().toString(), email.getText().toString(), phone.getText().toString(), true);
        guestDao.insert(newGuest);
    }

    private void closeDatabase()
    {
        daoSession.clear();
        guestBookDB.close();
        guestBookDBHelper.close();
    }

    private void closeReopenDatabase()
    {
        daoSession.clear();
        guestBookDB.close();
        guestBookDBHelper.close();

        guestBookDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        guestBookDB = guestBookDBHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(guestBookDB);

        //Create database and tables
        daoMaster.createAllTables(guestBookDB, true);

        //Create DaoSession
        daoSession = daoMaster.newSession();

        //Create customer addition/removal instances
        guestDao = daoSession.getGuestDao();
    }
}
