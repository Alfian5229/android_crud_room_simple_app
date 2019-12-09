package com.example.learnroomcrud;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.learnroomcrud.data.factory.AppDatabase;
import com.example.learnroomcrud.databinding.ActivityRoomCreateBinding;
import com.example.learnroomcrud.model.Barang;

import java.lang.ref.WeakReference;

public class RoomCreateActivity extends AppCompatActivity {

    private static AppDatabase db;
    private ActivityRoomCreateBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityRoomCreateBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        // initiate call Room database
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "barangdb").build();

        b.btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Barang barang = new Barang();
                barang.setHargaBarang(b.etHargabarang.getText().toString());
                barang.setMerkBarang(b.etMerkbarang.getText().toString());
                barang.setNamaBarang(b.etNamabarang.getText().toString());
                insertData(barang);
            }
        });
    }

    private void insertData(final Barang barang){
        new InsertDataTask(barang, RoomCreateActivity.this).execute();
    }

    private static class InsertDataTask extends AsyncTask<Void, Void, Long> {
        private Barang barang;
        // Weak references will still allow the Activity to be garbage-collected
        private final WeakReference<Activity> weakActivity;

        InsertDataTask(Barang barang, Activity weakActivity) {
            this.barang = barang;
            this.weakActivity = new WeakReference<>(weakActivity);
        }

        @Override
        protected Long doInBackground(Void... voids) {
            // do insert data process
            return db.barangDAO().insertBarang(barang);
        }

        @Override
        protected void onPostExecute(Long status) {
            Toast.makeText(weakActivity.get(), "status row "+status, Toast.LENGTH_SHORT).show();
        }
    }
}
