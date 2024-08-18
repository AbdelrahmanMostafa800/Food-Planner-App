package com.example.mealmate.homefragment.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mealmate.R;
import com.example.mealmate.homefragment.presenter.HomeFragmentPresenter;
import com.example.mealmate.homefragment.presenter.HomeFragmentPresenterImp;
import com.example.mealmate.model.Meal;
import com.example.mealmate.model.userrepo.UserReposatoryImp;
import com.example.mealmate.model.userrepo.UserReposatoryInterface;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class HomeFragment extends Fragment implements HomeFragmentView{

    private RecyclerView recyclerView;
//    private HomeFragmentRecycleAdapter myAdapter;
    HomeFragmentPresenter hpresenter;
    ImageView mealImageView;
    TextView mealName;

    public HomeFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserReposatoryInterface reposatory= UserReposatoryImp.getInstance(getContext());
        TextView nameText=view.findViewById(R.id.nameText);
         mealName=view.findViewById(R.id.mealNameView);
         mealImageView=view.findViewById(R.id.mealImageView);
        nameText.setText(getString(R.string.hellow)+reposatory.getUserLocalData()[1]+"!");
        ChipGroup chipGroup = view.findViewById(R.id.chipGroup);
//        myAdapter = new HomeFragmentRecycleAdapter();
//        recyclerView.setAdapter(myAdapter);
        hpresenter=new HomeFragmentPresenterImp(this);
        hpresenter.getSingleMeal();

        for(int i=0;i<chipGroup.getChildCount();i++){
            Chip chip=(Chip)chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        int checkedId = chipGroup.getCheckedChipId();
                        if (checkedId == R.id.chip_category) {
                            hpresenter.getSingleMeal();
                        } else if (checkedId == R.id.chip_countries) {

                        } else if (checkedId == R.id.chip_ingrediants) {

                        }

                    }
                }
            });
        }
    }

    @Override
    public void showMeal(Meal meal) {
        mealName.setText(meal.getStrMeal());
        Glide.with(mealImageView.getContext())
                .load(meal.getStrMealThumb())
                .into(mealImageView);
    }
}