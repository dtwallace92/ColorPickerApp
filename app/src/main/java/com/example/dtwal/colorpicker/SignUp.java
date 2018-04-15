package com.example.dtwal.colorpicker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    EditText firstName, lastName, signUpEmail, signUpPassword, confirmPassword;
    Button signUpConfirmButton, cancelButton;
    private FirebaseAuth mAuth;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final String TAG = "SignUp";

        firstName = findViewById(R.id.nameFirstSignEditText);
        lastName = findViewById(R.id.nameLastSignEditText);
        signUpEmail = findViewById(R.id.emailSignEditText);
        signUpPassword = findViewById(R.id.passwordSignEditText);
        confirmPassword = findViewById(R.id.confirmPassEditText);
        signUpConfirmButton = findViewById(R.id.confirmSignButton);
        cancelButton = findViewById(R.id.cancelSignButton);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

        //Close activity when Cancel is clicked
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Attempt to create user when Sign Up is clicked, if successful exit screen
        signUpConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() ||
                        signUpEmail.getText().toString().isEmpty() || signUpPassword.getText().toString().isEmpty() ||
                        confirmPassword.getText().toString().isEmpty()) {
                    Toast.makeText(SignUp.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                }
                else if (!(signUpPassword.getText().toString().equals(confirmPassword.getText().toString()))) {
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(signUpEmail.getText().toString(), signUpPassword.getText().toString())
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        //updateUI(user);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignUp.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }

            }
        });
    }
}
