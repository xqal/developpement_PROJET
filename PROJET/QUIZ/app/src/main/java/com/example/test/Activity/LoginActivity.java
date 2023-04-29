package com.example.test.Activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.R;

public class LoginActivity extends AppCompatActivity {
    private EditText PSD;
    private EditText MDP;
    private Button ConnectAcc;
    private TextView CreateAcc;
    private TextView errorText;
    private String Pseudo;
    private String Motdepasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PSD = findViewById(R.id.editTextTextPersonName);
        MDP = findViewById(R.id.editTextMOTDEPASSE);
        ConnectAcc = findViewById(R.id.LogButton);
        CreateAcc  = findViewById(R.id.textCreatACC);
        errorText = findViewById(R.id.textView7);


        ConnectAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ON RECUPERE LES VALEURS DES EDIT TEXT
                Pseudo = PSD.getText().toString();
                Motdepasse = MDP.getText().toString();

                //ON ACCEDE A LA BASE DE DONNEE AVEC LA CLASSE BASEMANAGER POUR LANCER UNE REQUETE
                final BaseManager db = BaseManager.getInstance(getApplicationContext());
                db.open();
                Boolean result = db.connectUser(Pseudo, Motdepasse);
                //SI LA METHODE CONNECTUSER EST VRAI ALORS L'UTILISATEUR PEUT SE CONNECTER
                if (result == true) {
                    Toast.makeText(getApplicationContext(), "Connecté !", Toast.LENGTH_SHORT).show();
                    Intent AccueilActivity = new Intent(getApplicationContext(), Activity_Accueuille.class);
                    startActivity(AccueilActivity);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Mot de passe ou pseudo incorrecte !", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });

        //CHANGE D'ACTIVITY'
        CreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createAccActivity = new Intent(getApplicationContext(), CreateAccActivity.class);
                startActivity(createAccActivity);
                finish();
            }
        });
    }
}