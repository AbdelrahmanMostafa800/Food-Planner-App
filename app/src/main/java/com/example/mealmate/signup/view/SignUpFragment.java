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
import android.widget.TextView;

import com.example.mealmate.R;
import com.example.mealmate.model.UserAuthReposatoryImp;
import com.example.mealmate.signup.presenter.SignUpPresenter;
import com.example.mealmate.signup.presenter.SignUpPresenterInterface;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpFragment extends Fragment implements SignUpView{

    SignUpPresenterInterface presenter;
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

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
        TextView errorText=view.findViewById(R.id.errorMessage);
        presenter=new SignUpPresenter(this,getContext());
        sigInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorText.setVisibility(View.INVISIBLE);
                if(!nameText.getEditText().getText().toString().isEmpty()&&!emailText.getEditText().getText().toString().isEmpty()&&!passowrdTex.getEditText().getText().toString().isEmpty()) {
                    if (!isValidEmail(emailText.getEditText().getText().toString())) {
                        presenter.createUserWithEmailPassword("SignUp", emailText.getEditText().getText().toString(), passowrdTex.getEditText().getText().toString(), nameText.getEditText().getText().toString());
                    }else{
                        emailText.setError("Invalid Email");
                    }
                }
                else{
                    errorText.setVisibility(View.INVISIBLE);
                }

            }
        });
    }
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}