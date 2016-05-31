package com.josepgrs.reminderalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccount extends MainActivity {

    private static final String TAG = "CreateAccount";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText newemailText;
    private EditText newpassText;
    private Button createAccbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);


        createAccbtn = (Button) findViewById(R.id.createAcc);
        newemailText = (EditText) findViewById(R.id.emailNewAcc);
        newpassText = (EditText) findViewById(R.id.passNewAcc);
        createAccbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(newemailText.getText().toString(), newpassText.getText().toString());


            }
        });
    }

    private void createAccount(String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }


        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                if (task.isSuccessful()) {
                    Intent i = new Intent(CreateAccount.this, MainActivity.class);
                    startActivity(i);
                }
                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Toast.makeText(CreateAccount.this, "Failed to Create.",
                            Toast.LENGTH_SHORT).show();
                }

                // [START_EXCLUDE]

                // [END_EXCLUDE]
            }
        });
        // [END create_user_with_email] */


    }

    private boolean validateForm() {
        boolean valid = true;

        String email = newemailText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            newemailText.setError("Required.");
            valid = false;
        } else {
            newemailText.setError(null);
        }

        String password = newpassText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            newpassText.setError("Required.");
            valid = false;
        } else {
            newpassText.setError(null);
        }

        return valid;
    }
}
