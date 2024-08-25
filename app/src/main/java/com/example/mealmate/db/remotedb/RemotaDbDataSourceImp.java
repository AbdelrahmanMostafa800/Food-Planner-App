package com.example.mealmate.db.remotedb;

import android.util.Log;

import com.example.mealmate.favoritsfragment.presenter.FirestorePresenterInterface;
import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

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
    FirestorePresenterInterface result;
    public RemotaDbDataSourceImp() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public Completable saveMealToDb(List<MealDb> mealDbsList) {
        Completable completabl=null;
        if(mealDbsList!=null){
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
            DocumentReference docRef = db.collection("mealmate").document(mealDb.getUserName()).collection("favoritmeals").document(mealDb.getIdMeal());
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
            completabl= completable;
        }}
        return completabl;
    }

    public Single<List<MealDb>> retrieveMealsFromFirestore(String userId) {
        //CollectionReference mealsRef = db.collection(userId);
        CollectionReference mealsRef = db.collection("mealmate").document(userId).collection("favoritmeals");
        Log.d("userid", userId);
        return Single.create(emitter -> {
            mealsRef.get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            List<MealDb> mealDbs = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MealDb mealDb = new MealDb();
                                Log.d("userid", userId);
                                Log.d("userid", document.getId());
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
    @Override
    public void saveDayMealToFirestore(List<DayMealDb> dayMelDb) {
        for (DayMealDb dayMealDb : dayMelDb) {
            Map<String, Object> dayMealMap = new HashMap<>();
            dayMealMap.put("Day", dayMealDb.getDay());
            dayMealMap.put("userName", dayMealDb.getUserName());
            dayMealMap.put("idMeal", dayMealDb.getIdMeal());
            dayMealMap.put("strMeal", dayMealDb.getStrMeal());
            dayMealMap.put("strCategory", dayMealDb.getStrCategory());
            dayMealMap.put("strArea", dayMealDb.getStrArea());
            dayMealMap.put("strInstructions", dayMealDb.getStrInstructions());
            dayMealMap.put("strYoutube", dayMealDb.getStrYoutube());
            List<String> ingredients = dayMealDb.getStrIngredients();
            List<String> measures = dayMealDb.getStrMeasures();
            for (int i = 1; i <= 20; i++) {
                String ingredientKey = "strIngredient" + i;
                String measureKey = "strMeasure" + i;
                dayMealMap.put(ingredientKey, ingredients.get(i - 1));
                dayMealMap.put(measureKey, measures.get(i - 1));
            }
            byte[] imageBytes = dayMealDb.getImage();
            Blob imageBlob = Blob.fromBytes(imageBytes);
            dayMealMap.put("image", imageBlob);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("mealmate").document(dayMealDb.getUserName()).collection("daymeals").document(dayMealDb.getIdMeal());
            docRef.set(dayMealMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d("Firestore", "Day meal saved successfully");
                } else {
                    Log.w("Firestore", "Error saving day meal", task.getException());
                }
            });
        }
    }
    @Override
    public Single<List<DayMealDb>> retrieveDayMealsFromFirestore(String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference dayMealsRef = db.collection("mealmate").document(userId).collection("daymeals");
        return Single.create(emitter -> {
            dayMealsRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    List<DayMealDb> dayMealDbs = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        DayMealDb dayMealDb = new DayMealDb();
                        dayMealDb.setDay(document.getString("Day"));
                        dayMealDb.setUserName(userId);
                        dayMealDb.setIdMeal(document.getId());
                        dayMealDb.setStrMeal(document.getString("strMeal"));
                        dayMealDb.setStrCategory(document.getString("strCategory"));
                        dayMealDb.setStrArea(document.getString("strArea"));
                        dayMealDb.setStrInstructions(document.getString("strInstructions"));
                        dayMealDb.setStrYoutube(document.getString("strYoutube"));
                        List<String> ingredients = new ArrayList<>();
                        List<String> measures = new ArrayList<>();
                        for (int i = 1; i <= 20; i++) {
                            String ingredientKey = "strIngredient" + i;
                            String measureKey = "strMeasure" + i;
                            ingredients.add(document.getString(ingredientKey));
                            measures.add(document.getString(measureKey));
                        }
                        dayMealDb.setIngredients(ingredients);
                        dayMealDb.seMeasures(measures);
                        Blob imageBlob = (Blob) document.get("image");
                        dayMealDb.setImage(imageBlob.toBytes());
                        dayMealDbs.add(dayMealDb);
                    }
                    emitter.onSuccess(dayMealDbs);
                } else {
                    emitter.onError(task.getException());
                }
            });
        });
    }
    }
