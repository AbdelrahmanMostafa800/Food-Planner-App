package com.example.mealmate.db.remotedb;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mealmate.model.MealDb;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RemotaDbDataSourceImp implements RemotaDbDataSource {
    private FirebaseFirestore db;

    public RemotaDbDataSourceImp() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void saveMealToDb(List<MealDb> mealDbsList) {
//        MealDb mealDb =mealDbsList.get(0);
        for (MealDb mealDb : mealDbsList) {
            Map<String, Object> mealMap = new HashMap<>();
            mealMap.put("userName", mealDb.getUserName());
            mealMap.put("idMeal", mealDb.getIdMeal());
            mealMap.put("strMeal", mealDb.getStrMeal());
            mealMap.put("strCategory", mealDb.getStrCategory());
            mealMap.put("strArea", mealDb.getStrArea());
            mealMap.put("strInstructions", mealDb.getStrInstructions());
            mealMap.put("strYoutube", mealDb.getStrYoutube());
            List<String> ingredients = mealDb.getStrIngredients();
            List<String> measures = mealDb.getStrMeasures();
            for (int i = 1; i < ingredients.size(); i++) {
                String ingredientKey = "strIngredient" + i;
                String ingredientValue = ingredients.get(i);
                mealMap.put(ingredientKey, ingredientValue);
            }

            for (int i = 1; i < measures.size(); i++) {
                String measureKey = "strMeasure" + i;
                String measureValue = measures.get(i);
                mealMap.put(measureKey, measureValue);
            }

            Blob imageBlob = Blob.fromBytes(mealDb.getImage());
            mealMap.put("image", imageBlob);
            //reconvert
            /*Blob imageBlob = (Blob) mealMap.get("image");
byte[] imageData = imageBlob.toBytes();*/

            DocumentReference docRef = db.collection(mealDb.getUserName()).document(mealDb.getIdMeal());
            Completable completable = Completable.fromCallable(() -> Tasks.await(docRef.set(mealMap)));
            completable.subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d("Firestore", "Subscribed to save meal");
                        }

                        @Override
                        public void onComplete() {
                            Log.d("Firestore", "Meal saved successfully");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.w("Firestore", "Error saving meal", e);
                        }
                    });
        }
    }

    public Single<List<MealDb>> retrieveMealsFromFirestore(String userId) {
        CollectionReference mealsRef = db.collection(userId);
        return Single.create(emitter -> {
            mealsRef.get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            List<MealDb> mealDbs = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MealDb mealDb = new MealDb();
                                mealDb.setIdMeal(document.getId());
                                mealDb.setUserName(userId);
                                mealDb.setStrMeal(document.getString("strMeal"));
                                mealDb.setStrCategory(document.getString("strCategory"));
                                mealDb.setStrArea(document.getString("strArea"));
                                mealDb.setStrInstructions(document.getString("strInstructions"));
                                mealDb.setStrYoutube(document.getString("strYoutube"));
                                List<String> ingredients = new ArrayList<>();
                                List<String> measures = new ArrayList<>();
                                for (int i = 1; i <= 20; i++) {
                                    String ingredientKey = "strIngredient" + i;
                                    String measureKey = "strMeasure" + i;
                                    ingredients.add(document.getString(ingredientKey));
                                    measures.add(document.getString(measureKey));
                                }
                                mealDb.setIngredients(ingredients);
                                mealDb.seMeasures(measures);
                                Blob imageBlob = (Blob) document.get("image");
                                mealDb.setImage(imageBlob.toBytes());
                                mealDbs.add(mealDb);
                            }
                            emitter.onSuccess(mealDbs);
                        } else {
                            emitter.onError(task.getException());
                        }
                    });
        });
    }
    }

//        CollectionReference mealsRef = db.collection("users").document("ahmed").collection("meals");
//        mealsRef.add(meal)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        Log.d("fav", "Meal saved successfully");
//                    } else {
//                        Log.e("fav", "Error saving meal", task.getException());
//                    }
//                });
//        docRef.set(mealMap);
//        docRef.set(mealMap)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d("Firestore", "Document saved successfully");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("Firestore", "Error saving document", e);
//                    }
//                });
   /* FirebaseDatabase database;
    DatabaseReference myRef;
 Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

myRef.setValue("Hello, World!");*//*
*//*    // Read from the database
myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            String value = dataSnapshot.getValue(String.class);
            Log.d(TAG, "Value is: " + value);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    });*/

