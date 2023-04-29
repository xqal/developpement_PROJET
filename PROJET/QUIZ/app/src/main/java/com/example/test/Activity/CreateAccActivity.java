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

public class CreateAccActivity extends AppCompatActivity {
    private EditText PSD;
    private EditText MDP;
    private Button CreateAcc;
    private TextView ConnectAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

        PSD = findViewById(R.id.editTextTextPersonName);
        MDP = findViewById(R.id.editTextMOTDEPASSE);
        CreateAcc = findViewById(R.id.LogButton);
        ConnectAcc  = findViewById(R.id.textCreatACC);

        CreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ON RECUPERE LES VALEURS DES EDIT TEXT
                String Pseudo = PSD.getText().toString();
                String Motdepasse = MDP.getText().toString();
                int score = 0;

                //ON ACCEDE A LA BASE DE DONNEE AVEC LA CLASSE BASEMANAGER POUR LANCER UNE REQUETE
                final BaseManager db = BaseManager.getInstance(getApplicationContext());
                db.open();
                Boolean result = db.createUser(Pseudo, Motdepasse, score);
                //SI LA METHODE INSERTUSER EST VRAI (LE PSEUDO N'EXISTE PAS DANS LA BASE) ALORS L'UTILISATEUR PEUT S'INSCRIRE
                if (result == true) {
                    Toast.makeText(getApplicationContext(), "Utilisateur inscrit avec succès", Toast.LENGTH_SHORT).show();
                    Intent connectAccActivity = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(connectAccActivity);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Ce pseudo est déjà pris", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });

        ConnectAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent connectAccActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(connectAccActivity);
                finish();
            }
        });
    }
}