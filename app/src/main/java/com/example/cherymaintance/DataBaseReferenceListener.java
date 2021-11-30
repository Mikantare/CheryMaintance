package com.example.cherymaintance;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class DataBaseReferenceListener {

    private ChildEventListener maintanceChildEventListener;



    public void getDataSnapchot(DatabaseReference usersDataBaseReference) {
        if (maintanceChildEventListener == null) {
            maintanceChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//               List<String> list = new ArrayList<>();
                    Log.d("TAGFIREBASE", snapshot.getKey());
//               list.add(snapshot.getKey());

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
            usersDataBaseReference.addChildEventListener(maintanceChildEventListener);
        }

    }

}
