package com.dounia.moveease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class page4 extends AppCompatActivity {
    private EditText e1 = null;
    private EditText e2 = null;
    private Button but = null;
    private TextView obl = null;
    private String password;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//This line hides the action bar
        setContentView(R.layout.activity_page4);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);


        obl= (TextView) findViewById(R.id.obl);
        but= (Button) findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = e2.getText().toString();
                email =e1.getText().toString();

//                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("user").child("Gmail");
//                reference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnSuccessListener(
                        new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent bus = new Intent(page3.this, page7.class);
                                startActivity(bus);
                            }
                        }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(page3.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        obl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bu = new Intent(page3.this,MainActivity.class);
                startActivity(bu);
            }
        });
    }
}