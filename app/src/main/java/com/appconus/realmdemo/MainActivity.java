package com.appconus.realmdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.appconus.realmdemo.models.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Calendar;

import io.realm.Realm;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeToDb();
        readFromDb();
        exportDatabase(Realm.DEFAULT_REALM_NAME);
    }

    private void writeToDb() {
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        User user = realm.createObject(User.class);
        user.setSessionId(1);
        user.setName("BinhCQ" + Calendar.getInstance().getTimeInMillis());
        user.setAge(23);
        realm.commitTransaction();
    }

    private void readFromDb() {
        Realm realm = Realm.getInstance(this);
        User user = realm.where(User.class).findFirst();
        Log.d(TAG, "Got user: " + user.getName());
    }

    private void exportDatabase(String databaseName) {
        String internalStoragePath =  getFilesDir().getPath();
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory.canWrite()) {
            File currentDB = new File(internalStoragePath, databaseName);
            File backupDB = new File(externalStorageDirectory, databaseName);
            if (currentDB.exists()) {
                copyDatabaseFileToSDCard(currentDB, backupDB);
            }
        }
    }

    private void copyDatabaseFileToSDCard(File currentDB, File backupDB) {
        try {
            FileChannel sourceFileChannel = new FileInputStream(currentDB).getChannel();
            FileChannel destinationFileChannel = new FileOutputStream(backupDB).getChannel();
            destinationFileChannel.transferFrom(sourceFileChannel, 0, sourceFileChannel.size());
            sourceFileChannel.close();
            destinationFileChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
