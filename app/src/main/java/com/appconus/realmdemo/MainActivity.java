package com.appconus.realmdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.appconus.realmdemo.models.User;

import io.realm.Realm;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeToDb();
        readFromDb();
    }

    private void writeToDb() {
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        User user = realm.createObject(User.class);
        user.setSessionId(1);
        user.setName("BinhCQ");
        user.setAge(23);
        realm.commitTransaction();
    }

    private void readFromDb() {
        Realm realm = Realm.getInstance(this);
        User user = realm.where(User.class).findFirst();
        Log.d(TAG, "Got user: " + user.getName());
    }
}
