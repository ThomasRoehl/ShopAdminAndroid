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
import android.widget.Toast;

import com.example.thomasroehl.shopadminandroid.R;
import com.example.thomasroehl.shopadminandroid.camera.CameraController;
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
        registerController = RegisterControllerImpl.getRegisterController();
        registerController.setCurrentActivityContext(this);

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

                if(registerController.checkUsername(username))
                {
                    return;
                }
                if(!registerController.verifyPassword(password, verifyPassword))
                {
                    return;
                }
                if(registerController.checkNameIsEmpty(username)  == true ||
                        registerController.checkPasswordIsEmpty(password) == true ||
                        registerController.checkEmailIsEmpty(email) == true)
                {
                    Toast toast = Toast.makeText(Login_Register_Activity.this, "Fill in all fields!", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                else {

                    Toast toast = Toast.makeText(Login_Register_Activity.this, "Registration successfull", Toast.LENGTH_LONG);
                    toast.show();
                    startActivity(new Intent(Login_Register_Activity.this, MainActivity.class));
                }

            }
        });
    }
}
