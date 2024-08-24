package com.example.mealmate.mealplane.view;

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

import com.example.mealmate.R;
import com.example.mealmate.favoritsfragment.view.MyFavProductsAdapter;
import com.example.mealmate.mealplane.presenter.MealPlaneFragmentPresenter;
import com.example.mealmate.mealplane.presenter.MealPlaneFragmentPresenterInterface;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;


public class MealPlaneFragment extends Fragment implements OnDayClickListener,MealPlaneFragmentView,OnCalenderClickListener{

    MealPlaneFragmentPresenterInterface presenter;
    RecyclerView mealPlaneRecycle;
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
        return inflater.inflate(R.layout.fragment_meal_plane, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.daysRecyclerView);
         mealPlaneRecycle = view.findViewById(R.id.mealPlaneRecycle);
        presenter=new MealPlaneFragmentPresenter(this,getContext());
        mealPlaneRecycle.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        List<String> chipTitles = Arrays.asList("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        DaysRecycleViewAdapter adapter = new DaysRecycleViewAdapter(chipTitles,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onDayClick(String day) {
        presenter.getLocalMealPlane(day,presenter.getUserID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // Switch to main thread
                .subscribe(mealDbs -> {
                    if (mealDbs.isEmpty()) {
                        Log.d("fav", "No favorite meals found");
                    } else {
                        MealPlaneAdapter adapter = new MealPlaneAdapter(mealDbs,this);
                        mealPlaneRecycle.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }, throwable -> {
                    Log.e("fav", "Error occurred", throwable);
                });
    }

    @Override
    public void deleteMealFromDb(String day, String userName, String idMeal) {
        presenter.deleteDayMealFromDb(day,userName,idMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    // Update the adapter after deletion
                    MealPlaneAdapter adapter = (MealPlaneAdapter) mealPlaneRecycle.getAdapter();
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }, throwable -> {
                    Log.e("fav", "Error occurred while deleting meal", throwable);
                });
    }
}