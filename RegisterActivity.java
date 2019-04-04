package com.vn8031.smartbus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText et_email,et_password,et_userName;
    private FirebaseAuth mAuth;
    ProgressDialog pg;
    DatabaseReference databaseReference;
    private DatabaseReference userIdRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_email = findViewById(R.id.et_emailId);
        et_password = findViewById(R.id.et_password);
        et_userName = findViewById(R.id.et_userName);

        mAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance()
                .getReference().child("SmartBus_Users");


        pg = new ProgressDialog(this);
        pg.setMessage("Registering...\n Please wait...");

    }

    public void save(View view)
    {
        final String userName = et_userName.getText().toString().trim();
        String emailId = et_email.getText().toString().trim();
        String password = et_password.getText().toString();

        if (userName.length() >= 4 & emailId.length() != 6 & password.length() >=6)
        {
            pg.show();

            mAuth.createUserWithEmailAndPassword(emailId,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isComplete())
                            {
                                pg.dismiss();
                                Toast.makeText(RegisterActivity.this, "Registered Successfully ..\n\t\t\t)", Toast.LENGTH_SHORT).show();
                                userIdRef = databaseReference.child(mAuth.getCurrentUser().getUid());
                                userIdRef.child("name").setValue(userName);

                                et_userName.setText(null);
                                et_password.setText(null);
                                et_email.setText(null);
                                Intent i = new Intent(getApplicationContext(),HomePage.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                //finish();
                            }
                        }
                    });

        }else
        {
            Toast.makeText(this,
                    "Enter UserName or emailId \n or \n create atleast 6 digits of Password", Toast.LENGTH_SHORT).show();
        }


    }

    public void reset(View view)
    {
        et_userName.setText(null);
        et_password.setText(null);
        et_email.setText(null);
    }
}
