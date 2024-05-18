package com.examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@SpringBootApplication
public class FirebaseTutorialApplication {

	
	
	public static void main(String[] args) throws IOException {
//		ClassLoader classLoader = FirebaseTutorialApplication.class.getClassLoader();
	/*
		FileInputStream serviceAccount =
				new FileInputStream("D:\\Self learning\\firebase\\serviceaccountkey.json");

				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .build();

				FirebaseApp.initializeApp(options);*/
		SpringApplication.run(FirebaseTutorialApplication.class, args);
	}
	
	
	@Bean
    public Firestore firestore() throws IOException {
        FileInputStream serviceAccount =
        		new FileInputStream("D:\\Self learning\\firebase\\serviceaccountkey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
               // .setProjectId("firebase-tutorial-db")
                .build();

        FirebaseApp.initializeApp(options);

        return FirestoreClient.getFirestore();
    }

}
