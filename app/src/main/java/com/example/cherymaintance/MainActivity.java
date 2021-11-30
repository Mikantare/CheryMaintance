package com.example.cherymaintance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference modelDataBaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelDataBaseReference = FirebaseDatabase.getInstance().getReference();
        DataBaseReferenceListener dataBaseReferenceListener = new DataBaseReferenceListener();
        dataBaseReferenceListener.getDataSnapchot(modelDataBaseReference);

    }
}