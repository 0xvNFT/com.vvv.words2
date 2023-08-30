package com.vvv.words2.Database;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class FirestoreDeleteHelper {

    private final FirebaseFirestore firestore;

    public FirestoreDeleteHelper() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void deleteDocument(String collectionName, String documentId) {
        DocumentReference documentRef = firestore.collection(collectionName).document(documentId);

        documentRef.delete()
                .addOnSuccessListener(aVoid -> {
                    Log.d("Firestore", "Document deleted successfully");
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error deleting document", e);
                });
    }

    public void deleteAllDocumentsInCollection(String collectionName) {
        CollectionReference collectionRef = firestore.collection(collectionName);

        collectionRef.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        documentSnapshot.getReference().delete();
                    }
                    Log.d("Firestore", "All documents in collection deleted successfully");
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error deleting documents", e);
                });
    }
}

