package com.example.cafenaporta.database;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Usuario.class}, version = 2)
public abstract class Database extends RoomDatabase {
    public abstract UsuarioDao getUserDao();
}
