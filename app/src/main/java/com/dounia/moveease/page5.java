package com.dounia.moveease;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.database.CursorJoiner;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.regex.Pattern;

public class page5 extends AppCompatActivity {

    private EditText edit1 = null;
    private EditText edit2 = null;
    private EditText edit10 = null;
    private EditText edit3 = null;
    private EditText edit4 = null;
    private EditText edit5 = null;
    private EditText edit6 = null;
    private EditText edit7 = null;
    private EditText add = null;
    private EditText sexe = null;
    private Button btn = null;
    private FirebaseAuth mAuth;
    String Nom = null;
    String Prénom = null;
    String Date_De_Naissance = null;
    String N_Téléphone = null;
    String Gmail = null;
    String Mot_De_Passe = null;
    String Confirmer_Mot_De_Passe = null;
    String Adresse = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//This line hides the action bar
        setContentView(R.layout.activity_page3);

        mAuth = FirebaseAuth.getInstance();
        //mAuth.loginWithEmailAndPassword(email,password)
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        edit10 = (EditText) findViewById(R.id.edit10);
        edit3 = (EditText) findViewById(R.id.edit3);
        edit4 = (EditText) findViewById(R.id.edit4);
        edit5 = (EditText) findViewById(R.id.edit5);
        edit6 = (EditText) findViewById(R.id.edit6);
        add = (EditText) findViewById(R.id.add);
        sexe = (EditText) findViewById(R.id.sexe);
        edit7 = (EditText) findViewById(R.id.edit7);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }


    private boolean isValidData() {

        boolean result = true;
        Nom = edit1.getText().toString();
        if (Nom.isEmpty()) {
            edit1.setError("Nom is requird!");
            edit1.requestFocus();
            result = false;

        }
        Prénom = edit2.getText().toString();
        if (Prénom.isEmpty()) {
            edit2.setError("Prénom is requird!");
            edit2.requestFocus();
            result = false;

        }
        Date_De_Naissance = edit10.getText().toString();
        if (Date_De_Naissance.isEmpty()) {
            edit10.setError("Date_De_Naissance is requird!");
            edit10.requestFocus();
            result = false;

        }
        if (!Fun.isvalidDate_De_Naissance(Date_De_Naissance)) {
            edit10.setError("Invalid Date_De_Naissance!");
            edit10.requestFocus();
            result = false;
        }


        N_Téléphone = edit3.getText().toString();
        if (N_Téléphone.isEmpty()) {
            edit3.setError("N°Téléphone is requird!");
            edit3.requestFocus();
            result = false;

        }
        if (!Fun.isvalidPhone(N_Téléphone)) {
            edit3.setError("Invalid N_Téléphone!");
            edit3.requestFocus();
            result = false;
        }


        Gmail = edit4.getText().toString();
        if (Gmail.isEmpty()) {
            edit4.setError("Gmail is requird!");
            edit4.requestFocus();
            result = false;
        }
        if (!Fun.isvalidEmail(Gmail)) {
            edit4.setError("Invalid Gmail!");
            edit4.requestFocus();
            result = false;
        }


        Adresse = add.getText().toString();
        if (Adresse.isEmpty()) {
            add.setError("Adresse is requird!");
            add.requestFocus();
            result = false;

        }


        Mot_De_Passe = edit5.getText().toString();
        if (Mot_De_Passe.isEmpty()) {
            edit5.setError(" Mot De Passe is requird!");
            edit5.requestFocus();
            result = false;
        }
        Confirmer_Mot_De_Passe = edit6.getText().toString();
        if (Confirmer_Mot_De_Passe.isEmpty()) {
            edit6.setError("Confirmer Mot De Passe is requird!");
            edit6.requestFocus();
            result = false;
        }

        if (Mot_De_Passe.length() < 8) {
            edit5.setError("Min Mot De Passe should be characters!");
            edit5.requestFocus();
            result = false;
        }
        if (Confirmer_Mot_De_Passe.length() < 8) {
            edit6.setError("Min Confirmer Mot De Passe should be characters!");
            edit6.requestFocus();
            result = false;
        }
        if (!Mot_De_Passe.equals(Confirmer_Mot_De_Passe)) {
            edit6.setError("Veuillez reconfirmer votre mot de passe !");
            edit6.requestFocus();
            result = false;

        }


        // result mzalt true
        return result;
    }

    private void registerUser() {

        if (isValidData()) {
            //MediaRouteButton progressBar = null;
            //progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(Gmail, Mot_De_Passe)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user user = new user(Nom, Prénom, Date_De_Naissance, N_Téléphone, Gmail, Groupe_Sanguin, Adresse, Sexe);


                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("user").child("Gmail");
                                reference.child("Nom").setValue(Nom);
                                reference.child("Prénom").setValue(Prénom);
                                reference.child("Date_De_Naissance").setValue(Date_De_Naissance);
                                reference.child("N_Téléphone").setValue(N_Téléphone);
                                reference.child("Gmail").setValue(Gmail);
                                reference.child("Adresse").setValue(Adresse);
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {

                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(page5.this, "user has been registered Successfully ", Toast.LENGTH_LONG).show();
                                                    //progressBar.setVisibility(View.VISIBLE);

                                                    //redirect to login Layout!

                                                } else {
                                                    Toast.makeText(page5.this, "Failed to register! Try again", Toast.LENGTH_LONG).show();
                                                    //progressBar.setVisibility(View.GONE);
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(page5.this, "Failed to register", Toast.LENGTH_LONG).show();
                                //progressBar.setVisibility(View.GONE);
                            }

                        }
                    });

        }
    }
}


