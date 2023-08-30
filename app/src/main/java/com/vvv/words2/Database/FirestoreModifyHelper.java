package com.vvv.words2.Database;

import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class FirestoreModifyHelper {

    private final FirebaseFirestore firestore;

    public FirestoreModifyHelper() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void updateDocument(String collectionName, String documentId, Map<String, Object> newData) {
        DocumentReference documentRef = firestore.collection(collectionName).document(documentId);

        documentRef.update(newData)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Firestore", "Document updated successfully");
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error updating document", e);
                });
    }
}
