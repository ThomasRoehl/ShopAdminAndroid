package com.example.thomasroehl.shopadminandroid.gui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.register.RegisterControllerImpl;

public class Login_Register_Activity extends AppCompatActivity {

    Button buttonRegister;
    EditText editTextUsername;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextVerifyPassword;
    TextView textViewUsernameMessage;
    TextView textViewVerifyPasswordMessage;

    RegisterControllerImpl registerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        registerController = new RegisterControllerImpl();
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextVerifyPassword = (EditText) findViewById(R.id.editTextVerifyPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonRegister = (Button) findViewById(R.id.buttonRegistrate);

        textViewUsernameMessage = (TextView) findViewById(R.id.textViewUsernameMessage);
        textViewVerifyPasswordMessage = (TextView) findViewById(R.id.textViewPasswordMessage);

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
               /* System.out.println("editTextUserName.beforeTextChanged-event-handler, " +
                " s = " + s + " start = " + start + " count = " + count + " after = " + after);*/
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*System.out.println("editTextUserName.onTextChanged-event-handler, " +
                        " s = " + s + " start = " + start + " count = " + count + " before = " + before);*/

            }
        });

        editTextVerifyPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {


            public void onFocusChange(View v, boolean hasFocus) {
                //System.out.println("verifyPassword lost focus event handler");
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
                System.out.println("register-button click event handler, begin");
                String username = editTextUsername.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String verifyPassword = editTextVerifyPassword.getText().toString();
                System.out.println("username = " + username);
                if(registerController.checkUsername(username))
                {
                    System.out.println("register-button click event handler, if 1");
                    return;
                }
                if(!registerController.verifyPassword(password, verifyPassword))
                {
                    System.out.println("register-button click event handler, if 2");
                    return;
                }
                //User user = new User(username, email, password);
                startActivity(new Intent(Login_Register_Activity.this, MainActivity.class));
            }
        });

    }
}
/*
*
*
*
* */