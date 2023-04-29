package com.example.test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import com.example.test.R;
import java.util.ArrayList;

public class QuizzActivity extends AppCompatActivity {
    private Question question;
    private ArrayList<Question> ListeQuestion;
    private TextView Id_question;
    private TextView Question;
    private TextView optionA;
    private TextView optionB;
    private TextView optionC;
    private TextView optionD;
    private ImageView reponseA;
    private ImageView reponseB;
    private ImageView reponseC;
    private ImageView reponseD;
    private Button QuestSuivante;
    private String OptionChoissis;
    private int score = 0;
    private int questionCounter = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        // ON ATTRIBUT LA VALEUR DES WIDGET DANS UNE VARIABLE
        Id_question = findViewById(R.id.textView);
        Question = findViewById(R.id.textQuestion);
        optionA = findViewById(R.id.textA);
        optionB = findViewById(R.id.textB);
        optionC = findViewById(R.id.textC);
        optionD = findViewById(R.id.textD);
        reponseA  = findViewById(R.id.imageA);
        reponseB  = findViewById(R.id.imageB);
        reponseC  = findViewById(R.id.imageC);
        reponseD  = findViewById(R.id.imageD);
        QuestSuivante  = findViewById(R.id.LogButton);


        Id_question.setText("Question :" +questionCounter);

        // ON OUVRE LA BASE
        final BaseManager db = BaseManager.getInstance(getApplicationContext());
        db.open();
        // A L'AIDE LA METHODE GETRANDOMQUESTIONS, NOUS OBTENONS UNE LISTE CONTENANT 10 QUESTIONS
        ListeQuestion = db.getRandomQuestions();
        //ON PREND LA PREMIERE QUESTION (AVEC LEURS OPTION ET REPONSE) DANS LA LISTE POUR L'AFFICHER DANS LES WIDGETS
        question = ListeQuestion.get(0);
        Question.setText(question.getQuestionText());

        String optionAText = question.getOptionA();
        String optionBText = question.getOptionB();
        String optionCText = question.getOptionC();
        String optionDText = question.getOptionD();
        optionA.setText(optionAText);
        optionB.setText(optionBText);
        optionC.setText(optionCText);
        optionD.setText(optionDText);

        //LE JOUEURS CLIQUE SUR LA REPONSE A,B...
        reponseA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionChoissis = question.getOptionA();
                // ON CHANGE LA COULEUR DE REPONSEA POUR MONTRER QU'IL EST BIEN SELECTIONNER
                reponseA.setColorFilter(getResources().getColor(R.color.selectedOptionColor));
                reponseB.clearColorFilter();
                reponseC.clearColorFilter();
                reponseD.clearColorFilter();
            }
        });

        reponseB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionChoissis = question.getOptionB();
                Toast.makeText(getApplicationContext(), "REPONSE CHOISIS : "+question.getReponse(), Toast.LENGTH_SHORT).show();
                reponseB.setColorFilter(getResources().getColor(R.color.selectedOptionColor));
                reponseA.clearColorFilter();
                reponseC.clearColorFilter();
                reponseD.clearColorFilter();
            }
        });

        reponseC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question.getReponse().equals("C")) {
                    // LA REPONSE EST CORRECTE
                    score++;
                }
                OptionChoissis = "C";
                reponseC.setColorFilter(getResources().getColor(R.color.selectedOptionColor));
                reponseA.clearColorFilter();
                reponseB.clearColorFilter();
                reponseD.clearColorFilter();
            }
        });

        reponseD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question.getReponse().equals("D")) {
                    // LA REPONSE EST CORRECTE
                    score++;
                }
                OptionChoissis = "D";
                reponseD.setColorFilter(getResources().getColor(R.color.selectedOptionColor));
                reponseA.clearColorFilter();
                reponseB.clearColorFilter();
                reponseC.clearColorFilter();
            }
        });

        //ON PASSE A LA QUESTION SUIVANTE
        QuestSuivante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reponse = question.getReponse();
                if (reponse.equals(OptionChoissis)) {
                    // LA REPONSE EST CORRECTE
                    score++;
                    if (reponse.equals(question.getOptionA())) {
                        reponseA.clearColorFilter();
                        reponseA.setColorFilter(getResources().getColor(R.color.teal_200));
                    }else if (reponse.equals(question.getOptionB())) {
                        reponseB.clearColorFilter();
                        reponseB.setColorFilter(getResources().getColor(R.color.teal_200));
                    } else if (reponse.equals(question.getOptionC())) {
                        reponseC.clearColorFilter();
                        reponseC.setColorFilter(getResources().getColor(R.color.teal_200));
                    } else if (reponse.equals(question.getOptionD())) {
                        reponseD.clearColorFilter();
                        reponseD.setColorFilter(getResources().getColor(R.color.teal_200));
                    }
                    Toast.makeText(getApplicationContext(), "Bien vu !", Toast.LENGTH_SHORT).show();
                } else {
                    if (reponse.equals(question.getOptionA())) {
                        reponseA.setColorFilter(getResources().getColor(R.color.teal_200));
                        reponseB.setColorFilter(getResources().getColor(R.color.incorrecte));
                        reponseC.setColorFilter(getResources().getColor(R.color.incorrecte));
                        reponseD.setColorFilter(getResources().getColor(R.color.incorrecte));
                    } else if (reponse.equals(question.getOptionB())) {
                        reponseB.setColorFilter(getResources().getColor(R.color.teal_200));
                        reponseA.setColorFilter(getResources().getColor(R.color.incorrecte));
                        reponseC.setColorFilter(getResources().getColor(R.color.incorrecte));
                        reponseD.setColorFilter(getResources().getColor(R.color.incorrecte));
                    } else if (reponse.equals(question.getOptionB())) {
                    reponseC.setColorFilter(getResources().getColor(R.color.teal_200));
                    reponseA.setColorFilter(getResources().getColor(R.color.incorrecte));
                    reponseB.setColorFilter(getResources().getColor(R.color.incorrecte));
                    reponseD.setColorFilter(getResources().getColor(R.color.incorrecte));
                    } else {
                        reponseD.setColorFilter(getResources().getColor(R.color.teal_200));
                        reponseA.setColorFilter(getResources().getColor(R.color.incorrecte));
                        reponseC.setColorFilter(getResources().getColor(R.color.incorrecte));
                        reponseB.setColorFilter(getResources().getColor(R.color.incorrecte));
                    }
                    Toast.makeText(getApplicationContext(), "La prochaine sera la bonne !", Toast.LENGTH_SHORT).show();
                }

                Question.setText("La reponse était :   " +reponse);
                OptionChoissis = "";

                if (ListeQuestion.size() > 1) {
                    // ON RETIRE LES QUESTIONS DEJA POSER ET ON PASSE A LA SUIVANTE
                    ListeQuestion.remove(0);
                    // METHODE PERMETTANT UN DELAI DE 3s AVANT DE PASSER LA QUESTION SUIVANTE
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            question = ListeQuestion.get(0);
                            Question.setText(question.getQuestionText());
                            String optionAText = question.getOptionA();
                            String optionBText = question.getOptionB();
                            String optionCText = question.getOptionC();
                            String optionDText = question.getOptionD();
                            optionA.setText(optionAText);
                            optionB.setText(optionBText);
                            optionC.setText(optionCText);
                            optionD.setText(optionDText);
                            reponseA.clearColorFilter();
                            reponseB.clearColorFilter();
                            reponseC.clearColorFilter();
                            reponseD.clearColorFilter();

                            // ON MET A JOUR LE NUMEROS DE LA QUESTION
                            questionCounter++;
                            Id_question.setText("Question : " + questionCounter);
                        }
                    }, 3000); // délai en millisecondes

                } else {
                    // LISTEQUESTION EST VIDE DONC LES 10 QUESTIONS SONT PASSEES, ON RETOURNE AU DEBUT
                    Toast.makeText(getApplicationContext(), "Bravo ! Vous avez obtenu " +score +" points", Toast.LENGTH_SHORT).show();
                    Intent AccueilActivity = new Intent(getApplicationContext(), Activity_Accueuille.class);
                    startActivity(AccueilActivity);
                    finish();
                }
            }
        });
        db.close();
    }
}