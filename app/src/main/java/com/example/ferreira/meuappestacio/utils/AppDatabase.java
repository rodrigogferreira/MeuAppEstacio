package com.example.ferreira.meuappestacio.utils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.diogosoares.estacioapp.daos.TaskDao;
import com.diogosoares.estacioapp.daos.UserDao;
import com.diogosoares.estacioapp.models.Task;
import com.diogosoares.estacioapp.models.User;

@Database(entities = {User.class, Task.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static final String databaseName = "estacio_app";

    private static AppDatabase instance;
    public abstract UserDao userDao();
    public abstract TaskDao taskDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null || !instance.isOpen()) {
            instance = Room.databaseBuilder(context, AppDatabase.class, databaseName)
                    .build(); }
        return instance; }}