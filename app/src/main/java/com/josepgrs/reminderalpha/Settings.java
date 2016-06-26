package com.josepgrs.reminderalpha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Settings extends AppCompatActivity {
    TextView groupDialog;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        groupDialog = (TextView) findViewById(R.id.groupdialog);
        groupDialog.setVisibility(View.INVISIBLE);
        groupDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.DialogFragment d = new GroupDialog();
                d.show(getFragmentManager(), "Create a group");
            }
        });
       mDatabase.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               Boolean groupAvailable = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("group").exists();
               if (groupAvailable) {
                   groupDialog.setVisibility(View.GONE);
               } else {
                   groupDialog.setVisibility(View.VISIBLE);
               }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }
}
