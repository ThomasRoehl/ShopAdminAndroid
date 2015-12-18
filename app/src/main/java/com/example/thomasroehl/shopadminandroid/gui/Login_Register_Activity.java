package com.example.thomasroehl.shopadminandroid.gui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.container.User;
import com.example.thomasroehl.shopadminandroid.register.RegisterControllerImpl;
import com.example.thomasroehl.shopadminandroid.statics.StorageAdmin;

public class Login_Register_Activity extends AppCompatActivity {

    boolean inCreateNewAccountMode;
    Button buttonLogin;
    Button buttonRegister;
    Button buttonCreateNewAccount;
    EditText editTextUsername;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextVerifyPassword;
    TextView textViewUsernameMessage;
    TextView textViewVerifyPasswordMessage;
    TextView textViewEmail;
    TextView textViewVerifyPassword;
    TextView textViewInfo;

    RegisterControllerImpl registerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        inCreateNewAccountMode = false;

        registerController = new RegisterControllerImpl();
        registerController = RegisterControllerImpl.getRegisterController();
        registerController.setCurrentActivityContext(this);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextVerifyPassword = (EditText) findViewById(R.id.editTextVerifyPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        buttonCreateNewAccount = (Button) findViewById(R.id.buttonCreateNewAccount);
        textViewVerifyPassword = (TextView) findViewById(R.id.textViewVerifyPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        textViewUsernameMessage = (TextView) findViewById(R.id.textViewUsernameMessage);
        textViewVerifyPasswordMessage = (TextView) findViewById(R.id.textViewPasswordMessage);
        textViewInfo = (TextView) findViewById(R.id.textViewInfo);


        editTextUsername.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                System.out.println("afterTextChanged event handler");
                if (registerController.checkUsername(s.toString())) {
                    textViewUsernameMessage.setText("Username already in use. Choose another one!");
                    textViewUsernameMessage.setTextColor(Color.RED);
                    return;
                } else {
                    textViewUsernameMessage.setText("");
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        editTextVerifyPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!registerController.verifyPassword(editTextPassword.getText().toString(), editTextVerifyPassword.getText().toString())) {
                        textViewVerifyPasswordMessage.setText("These Passwords don't match. Please, try again!");
                        textViewVerifyPasswordMessage.setTextColor(Color.RED);
                    } else {
                        textViewVerifyPasswordMessage.setText("");
                    }
                } else {
                    textViewVerifyPasswordMessage.setText("");
                }
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String verifyPassword = editTextVerifyPassword.getText().toString();
                if (validateUserInputData(username, password, verifyPassword, email)) {
                    User user = new User(username,email, password);
                    StorageAdmin.REGISTERCONTROLLER.createUser(user);
                    //startActivity(new Intent(Login_Register_Activity.this, MainActivity.class));
                    startActivity(registerController.screenFlowMain());
                    Toast.makeText(Login_Register_Activity.this, "Registration successfull!", Toast.LENGTH_LONG).show();
                }

            }
        });
        buttonCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inCreateNewAccountMode = true;
                editTextEmail.setVisibility(View.VISIBLE);
                textViewEmail.setVisibility(View.VISIBLE);
                textViewVerifyPasswordMessage.setVisibility(View.VISIBLE);
                editTextVerifyPassword.setVisibility(View.VISIBLE);
                textViewVerifyPassword.setVisibility(View.VISIBLE);
                buttonCreateNewAccount.setVisibility(View.INVISIBLE);
                buttonRegister.setVisibility(View.VISIBLE);
                textViewInfo.setText("Create new Account");
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inCreateNewAccountMode){
                    inCreateNewAccountMode = false;
                    editTextEmail.setVisibility(View.INVISIBLE);
                    textViewEmail.setVisibility(View.INVISIBLE);
                    textViewVerifyPasswordMessage.setVisibility(View.INVISIBLE);
                    editTextVerifyPassword.setVisibility(View.INVISIBLE);
                    textViewVerifyPassword.setVisibility(View.INVISIBLE);
                    buttonCreateNewAccount.setVisibility(View.VISIBLE);
                    buttonRegister.setVisibility(View.INVISIBLE);
                    textViewInfo.setText("Login to existing account");
                }else{
                    //TODO: look into database and verify username and password
                    System.out.println("Not implemented yet");
                }
            }
        });
    }
    public boolean validateUserInputData(String username, String password, String verifyPassword, String email){
        if (registerController.checkUsername(username)) {
            return false;
        }
        if (!registerController.verifyPassword(password, verifyPassword)) {
            return false;
        }
        if (registerController.checkNameIsEmpty(username) == true ||
                registerController.checkPasswordIsEmpty(password) == true ||
                registerController.checkEmailIsEmpty(email) == true) {
            Toast.makeText(Login_Register_Activity.this, "Fill in all fields!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (registerController.checkEmailIsValid(email) == true) {
            Toast.makeText(Login_Register_Activity.this, "Enter a valid email address!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (registerController.checkPasswordIsTooShort(password) == true) {
            Toast.makeText(Login_Register_Activity.this, "Password is too short!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (registerController.checkPasswordIsTooLong(password) == true) {
            Toast.makeText(Login_Register_Activity.this, "Password is too long!", Toast.LENGTH_LONG).show();
            return false;

        }
        else
            return true;
    }
}
