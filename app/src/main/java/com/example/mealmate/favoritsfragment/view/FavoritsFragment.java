package com.example.mealmate.favoritsfragment.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mealmate.R;
import com.example.mealmate.favoritsfragment.presenter.FavoritsPresenter;
import com.example.mealmate.favoritsfragment.presenter.FavoritsPresenterInterface;
import com.example.mealmate.mealplane.presenter.MealPlaneFragmentPresenter;
import com.example.mealmate.model.MealDb;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class FavoritsFragment extends Fragment implements FavoritsFragmentView,OnFavClickListener{

    RecyclerView recyclerView;
    FavoritsPresenterInterface presenter;
    ImageView saveToCloud;
    List<MealDb> mealDbsList;
    public FavoritsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        presenter=new FavoritsPresenter(this,getContext());
        if(presenter.getUserStatus().equals("Guest")){
            return inflater.inflate(R.layout.guest_please_login, container, false);
        }else {
            return inflater.inflate(R.layout.fragment_favorits, container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!presenter.getUserStatus().equals("Guest")) {
            recyclerView = view.findViewById(R.id.favorits_recycle);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            saveToCloud = view.findViewById(R.id.cloud);
            presenter.getLocalFavorits(presenter.getUserId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()) // Switch to main thread
                    .subscribe(mealDbs -> {
                        if (mealDbs.isEmpty()) {
                            Log.d("fav", "No favorite meals found");
                        } else {
                            mealDbsList = mealDbs;
                            MyFavProductsAdapter adapter = new MyFavProductsAdapter(mealDbs, this);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }, throwable -> {
                        Log.e("fav", "Error occurred", throwable);
                    });
            saveToCloud.setOnClickListener(v -> {
                presenter.saveToFirebaseDb(mealDbsList);
            });
        }
    }


    @Override
    public void deleteMealFromDb(String userName, String idMeal) {
        presenter.deleteMealFromDb(userName, idMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    // Update the adapter after deletion
                    MyFavProductsAdapter adapter = (MyFavProductsAdapter) recyclerView.getAdapter();
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }, throwable -> {
                    Log.e("fav", "Error occurred while deleting meal", throwable);
                });
    }
}