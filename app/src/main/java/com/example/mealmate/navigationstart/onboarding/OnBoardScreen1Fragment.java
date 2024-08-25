package com.example.mealmate.navigationstart.onboarding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealmate.R;

public class OnBoardScreen1Fragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_board_screen1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button next=view.findViewById(R.id.nextbtn);
        TextView skip=view.findViewById(R.id.skip);
        skip.setOnClickListener(v->{
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_OnBoardScreen1Fragment_to_loginFragment);
        });
       // Navigation.findNavController(next).navigate(R.id.action_onBoardScreen1Fragment_to_onBoardScreen2Fragment);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_onBoardScreen1Fragment_to_onBoardScreen2Fragment);
            }
        });
    }
}