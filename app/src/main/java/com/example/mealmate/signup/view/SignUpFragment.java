package com.example.mealmate.signup.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealmate.HomeActivity;
import com.example.mealmate.R;
import com.example.mealmate.signup.presenter.SignUpPresenter;
import com.example.mealmate.signup.presenter.SignUpPresenterInterface;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpFragment extends Fragment implements SignUpView{

    SignUpPresenterInterface presenter;
    View view;
    TextView errorText;
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    boolean isErrorMsgVisble;

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
        this.view=view;
        super.onViewCreated(view, savedInstanceState);
        TextInputLayout nameText=view.findViewById(R.id.nameText);
        TextInputLayout emailText=view.findViewById(R.id.emailText);
        TextInputLayout passowrdTex=view.findViewById(R.id.passowrdTex);
        Button sigInBtn=view.findViewById(R.id.sigInBtn);
         errorText=view.findViewById(R.id.errorMessage);
        presenter=new SignUpPresenter(this,getContext());
        errorText.setVisibility(View.INVISIBLE);
        isErrorMsgVisble=false;
        sigInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isErrorMsgVisble){
                    errorText.setVisibility(View.INVISIBLE);
                    isErrorMsgVisble=false;
                }
                if(!nameText.getEditText().getText().toString().isEmpty()
                        && !nameText.getEditText().getText().toString().equals(getString(R.string.enter_name))
                        && !emailText.getEditText().getText().toString().isEmpty()
                        && !passowrdTex.getEditText().getText().toString().isEmpty()
                        && !passowrdTex.getEditText().getText().toString().equals(getString(R.string.enter_password))){
                    if (isValidEmail(emailText.getEditText().getText().toString())) {
                        presenter.createUserWithEmailPassword( emailText.getEditText().getText().toString(), passowrdTex.getEditText().getText().toString(), nameText.getEditText().getText().toString());
                    }else{
                        errorText.setText("Invalid Email");
                        errorText.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    errorText.setText(R.string.errormsg);
                    errorText.setVisibility(View.VISIBLE);
                }

            }
        });
    }
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void userAddSuccess() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

    @Override
    public void userAddSerror() {
        errorText.setText("Not Valid User");
        errorText.setVisibility(View.VISIBLE);
        isErrorMsgVisble=true;
    }
}
