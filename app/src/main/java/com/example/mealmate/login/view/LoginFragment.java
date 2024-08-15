package com.example.mealmate.login.view;

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
import com.example.mealmate.login.presenter.LoginPresenter;
import com.example.mealmate.login.presenter.LoginPresenterImp;
import com.example.mealmate.signup.presenter.SignUpPresenter;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment implements LoginView {
    LoginPresenter presenter;

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
        TextView createAccount=view.findViewById(R.id.createAccount);
        TextInputLayout emailText=view.findViewById(R.id.emailText);
        TextInputLayout passowrdTex=view.findViewById(R.id.passowrdTex);
        presenter=new LoginPresenterImp(this,getContext());
        Button loginBtn=view.findViewById(R.id.sigInBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginUser(emailText.getEditText().getText().toString(),passowrdTex.getEditText().getText().toString());
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
}