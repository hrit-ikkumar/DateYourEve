package com.example.dateyoureve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    // Firebase Authentication Credentials
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private EditText mCountryCode;
    private EditText mPhoneNumber;

    private Button mGenerateBtn;
    private TextView mLoginFeedbackText;

    // Callback
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // layout set as activity_login

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        // extracted all the values from the layout
        mCountryCode = findViewById(R.id.country_code);
        mPhoneNumber = findViewById(R.id.phone_number);
        mGenerateBtn = findViewById(R.id.login_button);
        mLoginFeedbackText = findViewById(R.id.login_form_feedback);

        // Event Listener for Generate OTP button
        mGenerateBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String country_code = mCountryCode.getText().toString();
                String phone_number = mPhoneNumber.getText().toString();

                // complete phone number example = "+91 8209062638"
                String complete_phone_number = "+" + country_code+ " " + phone_number;

                if(country_code.isEmpty() || phone_number.isEmpty())
                {
                    mLoginFeedbackText.setText(R.string.please_fill_in_the_form_to_continue);
                    mLoginFeedbackText.setVisibility(View.VISIBLE);
                }
                else
                {
                    mGenerateBtn.setEnabled(false);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            complete_phone_number,
                            60,
                            TimeUnit.SECONDS,
                            LoginActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    signInWithPhoneAuthCredential(phoneAuthCredential);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                    if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                        // Invalid request
                                        // ...
                                        mLoginFeedbackText.setText(R.string.please_fill_in_the_form_to_continue);
                                    } else if (e instanceof FirebaseTooManyRequestsException) {
                                        // The SMS quota for the project has been exceeded
                                        // ...
                                        mLoginFeedbackText.setText(R.string.sms_quota_exceeded);
                                    }
                                    else
                                        mLoginFeedbackText.setText(e.getMessage());
                                    mLoginFeedbackText.setVisibility(View.VISIBLE);
                                    mGenerateBtn.setEnabled(true);
                                }

                                @Override
                                public void onCodeSent(@NonNull final String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(s, forceResendingToken);
                                    new android.os.Handler().postDelayed(
                                            new Runnable() {
                                                public void run() {
                                                    Intent otpIntent = new Intent(LoginActivity.this, OTPVerification.class);
                                                    otpIntent.putExtra("AuthCredentials",s);
                                                    startActivity(otpIntent);
                                                }
                                            }, 70
                                    );
                                }
                            }
                    );
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mCurrentUser != null)
        {
            sendUserToHome();
        }
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            /*Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();*/
                            // ...
                            sendUserToHome();

                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                mLoginFeedbackText.setVisibility(View.VISIBLE);
                                mLoginFeedbackText.setText("There was an error verifying Phone number");
                            }
                        }
                        mGenerateBtn.setEnabled(true);
                    }
                });
    }

    private void sendUserToHome() {
        Intent homeIntent = new Intent(LoginActivity.this, LogOut.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
    }
}