package com.example.mealmate.signup.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mealmate.R;
import com.example.mealmate.model.UserAuthReposatoryImp;
import com.example.mealmate.signup.presenter.SignUpPresenter;
import com.example.mealmate.signup.presenter.SignUpPresenterInterface;
import com.google.android.material.textfield.TextInputLayout;


public class SignUpFragment extends Fragment implements SignUpView{

    SignUpPresenterInterface presenter;
    public SignUpFragment() {
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputLayout nameText=view.findViewById(R.id.nameText);
        TextInputLayout emailText=view.findViewById(R.id.emailText);
        TextInputLayout passowrdTex=view.findViewById(R.id.passowrdTex);
        Button sigInBtn=view.findViewById(R.id.sigInBtn);
        presenter=new SignUpPresenter(this,getContext());
        sigInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               presenter.createUserWithEmailPassword(emailText.getEditText().getText().toString(),passowrdTex.getEditText().getText().toString(),nameText.getEditText().getText().toString());

            }
        });
    }
}