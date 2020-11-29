package com.tallerdevehiculos.reparaciones.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FirebaseConfig {
    private static Firestore db=null;

    public static Firestore getInstance() {
        if (db == null){

            InputStream serviceAccount = null;
            try {
                serviceAccount = new FileInputStream("taller-de-vehiculos-968be-firebase-adminsdk-un54u-d192786e63.json");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            GoogleCredentials credentials = null;
            try {
                credentials = GoogleCredentials.fromStream(serviceAccount);
            } catch (IOException e) {
                e.printStackTrace();
            }
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);

            db = FirestoreClient.getFirestore();
            return db;
        }
        else{
            return db;
        }
    }

}
