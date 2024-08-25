package com.example.mealmate.mealplane.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mealmate.R;
import com.example.mealmate.favoritsfragment.view.MyFavProductsAdapter;
import com.example.mealmate.mealdetails.view.MealDetailsActivity;
import com.example.mealmate.mealplane.presenter.MealPlaneFragmentPresenter;
import com.example.mealmate.mealplane.presenter.MealPlaneFragmentPresenterInterface;
import com.example.mealmate.model.DayMealDb;
import com.example.mealmate.model.MealDb;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;


public class MealPlaneFragment extends Fragment implements OnDayClickListener,MealPlaneFragmentView,OnCalenderClickListener{

    MealPlaneFragmentPresenterInterface presenter;
    RecyclerView mealPlaneRecycle;
    ImageView saveToCloud;
    List<DayMealDb> mealDbsList;
    public MealPlaneFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        presenter=new MealPlaneFragmentPresenter(this,getContext());
        if(presenter.getUserStatus().equals("Guest")){
            return inflater.inflate(R.layout.guest_please_login, container, false);
        }else {
            return inflater.inflate(R.layout.fragment_meal_plane, container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=new MealPlaneFragmentPresenter(this,getContext());
        if(!presenter.getUserStatus().equals("Guest")){
            RecyclerView recyclerView = view.findViewById(R.id.daysRecyclerView);
            mealPlaneRecycle = view.findViewById(R.id.mealPlaneRecycle);
            saveToCloud = view.findViewById(R.id.cloud);
            mealPlaneRecycle.setLayoutManager(new GridLayoutManager(getContext(),2));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            List<String> chipTitles = Arrays.asList("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
            DaysRecycleViewAdapter adapter = new DaysRecycleViewAdapter(chipTitles,this);
            recyclerView.setAdapter(adapter);
            presenter.getAllLocalMealPlane(presenter.getUserID())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mealDbs -> {
                        if (mealDbs.isEmpty()) {
                            Toast.makeText(getContext(), "No meals found", Toast.LENGTH_SHORT).show();
                        } else {
                            mealDbsList = mealDbs;
                        }
                    }, throwable -> {
                        Log.e("fav", "Error occurred", throwable);
                        Toast.makeText(getContext(), "Can't get meals", Toast.LENGTH_SHORT).show();
                    });
            saveToCloud.setOnClickListener(v -> {
                presenter.saveToFirebaseDb(mealDbsList);
            });
        }


    }

    @Override
    public void onDayClick(String day) {
        presenter.getLocalMealPlane(day,presenter.getUserID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // Switch to main thread
                .subscribe(mealDbs -> {
                    if (mealDbs.isEmpty()) {
                        Log.d("fav", "No favorite meals found");
                        Toast.makeText(getContext(), "No meals found for this day", Toast.LENGTH_SHORT).show();
                    } else {
                        MealPlaneAdapter adapter = new MealPlaneAdapter(mealDbs,this);
                        mealPlaneRecycle.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }, throwable -> {
                   Toast.makeText(getContext(),"Can't get meals for this day", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void deleteMealFromDb(String day, String userName, String idMeal) {
        presenter.deleteDayMealFromDb(day,userName,idMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Toast.makeText(getContext(), "Meal deleted successfully", Toast.LENGTH_SHORT).show();
                    MealPlaneAdapter adapter = (MealPlaneAdapter) mealPlaneRecycle.getAdapter();
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }, throwable -> {
                 Toast.makeText(getContext(), "Can't delete meal\n try again ", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void showMealDeatails(DayMealDb dayMealDb) {
//        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
//        intent.putExtra("me",dayMealDb);
//        startActivity(intent);
    }
}