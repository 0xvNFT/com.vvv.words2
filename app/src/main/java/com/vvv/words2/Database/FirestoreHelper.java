package com.vvv.words2.Database;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.vvv.words2.Models.LevelModel;

import java.util.ArrayList;
import java.util.List;

public class FirestoreHelper {

    private final CollectionReference levelCollection;
    public FirebaseFirestore firestore;

    public FirestoreHelper() {
        firestore = FirebaseFirestore.getInstance();
        levelCollection = firestore.collection("levels");
    }

    public Task<DocumentReference> addLevel(LevelModel levelModel) {
        return levelCollection.add(levelModel);
    }

    public Task<List<LevelModel>> fetchLevels() {
        return levelCollection.orderBy("order", Query.Direction.ASCENDING).get().continueWith(task -> {
            List<LevelModel> levelModelList = new ArrayList<>();
            if (task.isSuccessful()) {
                List<DocumentSnapshot> levelsSnapshot = task.getResult().getDocuments();
                for (DocumentSnapshot document : levelsSnapshot) {
                    levelModelList.add(document.toObject(LevelModel.class));
                }
            }
            return levelModelList;
        });
    }


}