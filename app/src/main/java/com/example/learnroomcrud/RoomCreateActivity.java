package com.example.learnroomcrud;

import android.app.Activity;
import android.content.Intent;
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
import java.util.Objects;

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

        final Barang barang = (Barang) getIntent().getSerializableExtra("data");

        if(barang != null){
            b.etNamabarang.setText(barang.getNamaBarang());
            b.etMerkbarang.setText(barang.getMerkBarang());
            b.etHargabarang.setText(barang.getHargaBarang());
            b.btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    barang.setNamaBarang(Objects.requireNonNull(b.etNamabarang.getText()).toString());
                    barang.setMerkBarang(Objects.requireNonNull(b.etMerkbarang.getText()).toString());
                    barang.setHargaBarang(Objects.requireNonNull(b.etHargabarang.getText()).toString());
                    updateBarang(barang);
                }
            });
        }
        else{
            b.btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Barang barang = new Barang();
                    barang.setHargaBarang(Objects.requireNonNull(b.etHargabarang.getText()).toString());
                    barang.setMerkBarang(Objects.requireNonNull(b.etMerkbarang.getText()).toString());
                    barang.setNamaBarang(Objects.requireNonNull(b.etNamabarang.getText()).toString());
                    insertData(barang);
                }
            });
        }
    }

    private void insertData(final Barang barang){
        new InsertDataTask(barang, RoomCreateActivity.this).execute();
    }

    private void updateBarang(final Barang barang){
        new UpdateBarangTask(barang, RoomCreateActivity.this).execute();
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

    private static class UpdateBarangTask extends AsyncTask<Void, Void, Integer> {
        private Barang barang;
        // Weak references will still allow the Activity to be garbage-collected
        private final WeakReference<Activity> weakActivity;

        UpdateBarangTask(Barang barang, Activity weakActivity) {
            this.barang = barang;
            this.weakActivity = new WeakReference<>(weakActivity);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            // do update data process
            return db.barangDAO().updateBarang(barang);
        }

        @Override
        protected void onPostExecute(Integer status) {
            Toast.makeText(weakActivity.get(), "status row "+status, Toast.LENGTH_SHORT).show();
        }
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, RoomCreateActivity.class);
    }
}
