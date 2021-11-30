package com.example.cherymaintance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerModel;
    private Spinner spinnerCarComplection;

    private FirebaseAuth mAuth;
    private ChildEventListener maintanceChildEventListener;

    private List<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerModel = findViewById(R.id.spinnerModel);
        spinnerCarComplection = findViewById(R.id.spinnerCarComplection);
        DataBaseReferenceListener dataBaseReferenceListener = new DataBaseReferenceListener();
        getListModel();


    }

    private void getListModel() {
        DatabaseReference modelDataBaseReference = FirebaseDatabase.getInstance().getReference();
        getDataSnapchot(modelDataBaseReference, "model");
    }

        private void getListEquipment (String model) {
            DatabaseReference equipmentDataBaseReference = FirebaseDatabase.getInstance().getReference().child(model);
            getDataSnapchot(equipmentDataBaseReference, "equipment ");

    }

    public void getDataSnapchot(DatabaseReference dataBaseReference, String reference) {
        Log.d("TAGFIREBASE", reference);
        List<String> list = new ArrayList<>();
        if (maintanceChildEventListener == null) {
            maintanceChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d("TAGFIREBASE", snapshot.getKey());
                    list.add(snapshot.getKey());
                    switch (reference) {
                        case ("model"):
                        adapter = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_item, list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerModel.setAdapter(adapter);
                            Log.d("TAGFIREBASE", list.get(0));
                        break;
                        case ("equipment"): {
                            adapter = new ArrayAdapter<String>(MainActivity.this,
                                    android.R.layout.simple_spinner_item, list);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerCarComplection.setAdapter(adapter);
                            break;
                        }
                    }

                }


                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            dataBaseReference.addChildEventListener(maintanceChildEventListener);

        }
    }

}