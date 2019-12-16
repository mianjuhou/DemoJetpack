package com.potevio.app02;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = User.class, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    abstract UserDao userDao();

    private static AppDataBase INSTANCE;
    private static final Object sLock = new Object();

    public static AppDataBase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDataBase.class, "mydb.db")
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }
}
