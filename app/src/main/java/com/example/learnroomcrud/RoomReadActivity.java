package com.example.learnroomcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.learnroomcrud.adapter.AdapterBarangRecyclerView;
import com.example.learnroomcrud.data.factory.AppDatabase;
import com.example.learnroomcrud.databinding.ActivityRoomReadBinding;
import com.example.learnroomcrud.model.Barang;

import java.util.ArrayList;
import java.util.Arrays;

public class RoomReadActivity extends AppCompatActivity {

    ActivityRoomReadBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityRoomReadBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        //Initialize database allow main thread queries
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "barangdb").allowMainThreadQueries().build();

        //Initialize recyclerview dan layout manager
        b.rvMain.setHasFixedSize(true);
        b.rvMain.setLayoutManager(new LinearLayoutManager(this));

        //Add all data to arraylist
        ArrayList<Barang> daftarBarang = new ArrayList<>(Arrays.asList(db.barangDAO().selectAllBarangs()));

        //Set all data to adapter, and show it
        b.rvMain.setAdapter(new AdapterBarangRecyclerView(daftarBarang, this));
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, RoomReadActivity.class);
    }
}
