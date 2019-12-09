package com.example.learnroomcrud.data.factory;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.learnroomcrud.data.BarangDAO;
import com.example.learnroomcrud.model.Barang;

@Database(entities = {Barang.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BarangDAO barangDAO();

}
