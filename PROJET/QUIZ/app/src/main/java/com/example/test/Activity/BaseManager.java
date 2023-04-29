package com.example.test.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

// CLASSE PERMETTANT L'ACCESSIBILITE DE LA BASE DE DONNE.
// C'EST ICI QUE L'ON VA NOTER TOUTE LES REQUETES
public class BaseManager {
    private DatabaseHelper openHelper;
    private SQLiteDatabase db;
    private static BaseManager instance;
    Cursor c = null;

    private BaseManager(Context context) {this.openHelper = new DatabaseHelper(context);}

    public static BaseManager getInstance(Context context){
        if(instance==null){
            instance = new BaseManager(context);
        }
        return instance;
    }

    // OUVRE LA CONNEXION DE LA BASE DE DONNEE
    public void open() {
        this.db=openHelper.getWritableDatabase();
    }

    // FERME LA CONNEXION DE LA BASE DE DONNEE
    public void close(){
        if(db!=null){
            this.db.close();
        }
    }

    // REQUETES VERS LA BASE DE DONNEES //

    //  METHODE POUR INSERER UN JOUEUR
    public boolean createUser(String pseudo, String motDePasse, int score) {
        boolean result = false;
        // VERIFIE SI L'UTILISATEUR EST DEJA PRESENT
        c = db.rawQuery("SELECT * FROM tb_joueur WHERE pseudo = ?", new String[]{pseudo});
        if (c.moveToFirst()) {
            c.close();
        } else {
            // SINON ON L'INSERE DANS LA BASE
            ContentValues contentValues = new ContentValues();
            contentValues.put("pseudo", pseudo);
            contentValues.put("motdepasse", motDePasse);
            contentValues.put("score", 0);
            long l = db.insert("tb_joueur", null, contentValues);
            result = true;
        }
        return result;
    }

    // METHODE POUR CONNECTER L'UTILISATEUR
    public boolean connectUser(String pseudo, String motDePasse) {
        boolean result = false;
        // Vérifie si l'utilisateur est présent dans la base avec le pseudo et le mot de passe donnés
        c = db.rawQuery("SELECT * FROM tb_joueur WHERE pseudo = ? AND motdepasse = ?", new String[]{pseudo, motDePasse});
        if (c.moveToFirst()) {
            // Si l'utilisateur est présent, on retourne vrai
            result = true;
        }
        c.close();
        return result;
    }

    public ArrayList<Question> getRandomQuestions() {
        // ON RECUPERE 10 QUESTION ALEATOIRE
        String query = "SELECT * FROM tb_question ORDER BY RANDOM() LIMIT 10";
        // Exécute la requête et récupère le résultat dans un curseur
        c = db.rawQuery(query, null);

        // ON CREE UNE LISTE POUR STOCKER LES QUESTIONS
        ArrayList<Question> questionList = new ArrayList<>();

        // PARCOURT LE CURSEUR ET AJOUTE CHAQUE QUESTION A LA LISTE
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndexOrThrow("id"));
            String questionText = c.getString(c.getColumnIndexOrThrow("question"));
            String optionA = c.getString(c.getColumnIndexOrThrow("option_A"));
            String optionB = c.getString(c.getColumnIndexOrThrow("option_B"));
            String optionC = c.getString(c.getColumnIndexOrThrow("option_C"));
            String optionD = c.getString(c.getColumnIndexOrThrow("option_D"));
            String reponse = c.getString(c.getColumnIndexOrThrow("reponse"));
            Question question = new Question(id, questionText, optionA, optionB, optionC, optionD, reponse);
            questionList.add(question);
        }
        c.close();
        return questionList;
    }
}

