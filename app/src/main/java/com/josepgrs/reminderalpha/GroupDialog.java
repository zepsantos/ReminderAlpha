package com.josepgrs.reminderalpha;

import android.app.DialogFragment;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by josep on 07/06/2016.
 */
public class GroupDialog extends DialogFragment {
    private final static String TAG = "GroupDialog";
    private DatabaseReference mDatabase;
    private EditText ed;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.groupfragment, container,
                false);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        getDialog().setTitle("Create a group");
        // Do something else
      ed = (EditText) rootView.findViewById(R.id.groupname);

        TextView tv = (TextView) rootView.findViewById(R.id.createGroup);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createGroup(ed.getText().toString());
                getDialog().dismiss();
            }
        });

        return rootView;

    }

    private void createGroup(String groupname) {



        String useremail = mAuth.getCurrentUser().getEmail();

        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/users/" + mAuth.getCurrentUser().getUid() + "/group",   groupname);
        childUpdates.put("/groups/" + groupname + "/admin/", useremail);
        mDatabase.updateChildren(childUpdates);

    }


}
