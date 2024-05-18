package com.examples.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@Service
public class CRUDService {
	@Autowired
	private Firestore firestore;

	public User getUser(String document_id) {

        // Get a reference to the user document in Firestore
        DocumentSnapshot document;
        try {
            document = firestore.collection("crud_user").document(document_id).get().get();
            if (document.exists()) {
                // Map the document to a User object and return
                return document.toObject(User.class);
            } else {
                // User not found
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions
            System.err.println("Error retrieving user: " + e.getMessage());
            return null;
        }
    }
	

	public List<User> getAll() {
		List<User> userList = new ArrayList<>();

		// Get a reference to the "crud_user" collection
		CollectionReference usersRef = firestore.collection("crud_user");

		// Retrieve all documents in the collection
		ApiFuture<QuerySnapshot> future = usersRef.get();

		try {
			// Get the result of the future
			QuerySnapshot querySnapshot = future.get();

			// Iterate over the documents in the query snapshot
			for (QueryDocumentSnapshot document : querySnapshot) {
				// Map each document to a User object
				User user = document.toObject(User.class);
				userList.add(user);
			}

		} catch (InterruptedException | ExecutionException e) {
			System.err.println("Error retrieving users: " + e.getMessage());
		}

		return userList;
	}

	public String addUser(User user) {
		// Add the user to Firestore
		DocumentReference docRef = firestore.collection("crud_user").document(user.getDocument_id());
		ApiFuture<WriteResult> result = docRef.set(user);

		String resultReturn = "";
		try {
			resultReturn = "User successfully created: " + result.get().getUpdateTime();
		} catch (InterruptedException | ExecutionException e) {
			resultReturn = "Error creating user: " + e.getMessage();
		}
		return resultReturn;
	}

}
