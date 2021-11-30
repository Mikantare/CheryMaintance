package com.example.cherymaintance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

    private final String MODEL_REFERENCE = "model";
    private final String EQUIPEMENT_REFERENCE = "equipment";

    private Spinner spinnerModel;
    private Spinner spinnerCarComplection;

    private FirebaseAuth mAuth;
    private ChildEventListener maintanceChildEventListener;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerModel = findViewById(R.id.spinnerModel);
        spinnerCarComplection = findViewById(R.id.spinnerCarComplection);
        DataBaseReferenceListener dataBaseReferenceListener = new DataBaseReferenceListener();
        getListModel();

        spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String model = spinnerModel.getSelectedItem().toString();
                getListEquipment(model);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void getListModel() {
        DatabaseReference modelDataBaseReference = FirebaseDatabase.getInstance().getReference();
        getDataSnapchot(modelDataBaseReference, MODEL_REFERENCE);

    }

    private void getListEquipment(String model) {
        DatabaseReference equipmentDataBaseReference = FirebaseDatabase.getInstance().getReference().child(model);
        getDataSnapchot(equipmentDataBaseReference, EQUIPEMENT_REFERENCE);
    }

    private void assingValueSpiners(List<String> list, String reference) {
        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        switch (reference) {
            case MODEL_REFERENCE:
                spinnerModel.setAdapter(adapter);
                break;
            case EQUIPEMENT_REFERENCE:
                spinnerCarComplection.setAdapter(adapter);
                break;
        }
    }



    private void getDataSnapchot(DatabaseReference dataBaseReference, String reference) {
        List<String> list = new ArrayList<>();
        maintanceChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("TAGFIREBASE", snapshot.getKey());
                list.add(snapshot.getKey());
                assingValueSpiners(list, reference);
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