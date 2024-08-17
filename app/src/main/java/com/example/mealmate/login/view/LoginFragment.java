package com.example.mealmate.login.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealmate.R;
import com.example.mealmate.login.presenter.LoginPresenter;
import com.example.mealmate.login.presenter.LoginPresenterImp;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFragment extends Fragment implements LoginView {
    LoginPresenter presenter;
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public LoginFragment() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView createAccount = view.findViewById(R.id.createAccount);
        TextInputLayout emailText = view.findViewById(R.id.emailText);
        TextInputLayout passowrdTex = view.findViewById(R.id.passowrdTex);
        ImageView google = view.findViewById(R.id.google);
        presenter = new LoginPresenterImp(this, getContext());
        TextView errorText=view.findViewById(R.id.errorMessage);
        Button loginBtn = view.findViewById(R.id.sigInBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorText.setVisibility(View.INVISIBLE);
                if(!emailText.getEditText().getText().toString().isEmpty()&&!passowrdTex.getEditText().getText().toString().isEmpty()) {
                    if (!isValidEmail(emailText.getEditText().getText().toString())) {
                        presenter.loginUser("LoginedIn",emailText.getEditText().getText().toString(), passowrdTex.getEditText().getText().toString());
                    }else{
                        emailText.setError("Invalid Email");
                    }
                }
                else{
                    errorText.setVisibility(View.INVISIBLE);
                }
            }
        });
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });
    }
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}