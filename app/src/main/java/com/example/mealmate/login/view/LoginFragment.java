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

import com.example.mealmate.HomeActivity;
import com.example.mealmate.R;
import com.example.mealmate.login.presenter.LoginPresenter;
import com.example.mealmate.login.presenter.LoginPresenterImp;
import com.example.mealmate.signup.view.SignUpFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFragment extends Fragment implements LoginView {
    LoginPresenter presenter;
    boolean isErrorMsgVisble;
    GoogleSignInClient client;
    TextView errorText;
    View view;
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
        ImageView guest=view.findViewById(R.id.guestMode);
        emailText.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    emailText.getEditText().setText("");
                }
            }
        });
        passowrdTex.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    passowrdTex.getEditText().setText("");
                }
            }
        });

        presenter = new LoginPresenterImp(this, getContext());
         errorText = view.findViewById(R.id.errorMessage);
        Button loginBtn = view.findViewById(R.id.sigInBtn);
        errorText.setVisibility(View.INVISIBLE);
        isErrorMsgVisble = false;
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isErrorMsgVisble) {
                    errorText.setVisibility(View.INVISIBLE);
                    isErrorMsgVisble = false;
                }
                if (!emailText.getEditText().getText().toString().isEmpty()
                        && !passowrdTex.getEditText().getText().toString().isEmpty()
                        && !passowrdTex.getEditText().getText().toString().equals(getString(R.string.enter_password))) {
                    if (SignUpFragment.isValidEmail(emailText.getEditText().getText().toString())) {
                        presenter.loginUser(emailText.getEditText().getText().toString(), passowrdTex.getEditText().getText().toString());
                    } else {
                        errorText.setText("Invalid Email");
                        errorText.setVisibility(View.VISIBLE);
                    }
                } else {
                    errorText.setText(R.string.errormsg);
                    errorText.setVisibility(View.VISIBLE);
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
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(getActivity(),options);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              presenter.googleLogin();
                Intent i = client.getSignInIntent();
                startActivityForResult(i,1234);
            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginUserGuest();
            }
        });

    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(getActivity(),HomeActivity.class);
                                    startActivity(intent);

                                }else {
                                    Toast.makeText(view.getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            } catch (ApiException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

    @Override
    public void loginfail() {
        errorText.setText("Not Valid User");
        errorText.setVisibility(View.VISIBLE);
        isErrorMsgVisble=true;
    }
}