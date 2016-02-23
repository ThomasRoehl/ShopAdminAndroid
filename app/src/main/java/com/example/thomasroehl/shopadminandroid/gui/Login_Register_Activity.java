package com.example.thomasroehl.shopadminandroid.gui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
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

    // declare buttons, editText and textView fields
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
    TextView textViewEmailMessage;
    TextView textViewPasswordMessage;



    RegisterControllerImpl registerController;
    // Katia & Iuliia 04.01
    // calls directly dbcontroller


    // settings (tanja)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_register, menu);
        return true;
    }

    // settings (tanja)
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_exit){
            StorageAdmin.finishAll();
            return true;
        }

        if (id == R.id.action_help){

            Intent i = new Intent(
                    this.getApplicationContext(),
                    MakeFoto.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_logout){
            //TO DO
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.idToolbarEdit1);
        setSupportActionBar(toolbar);
        inCreateNewAccountMode = false;
        StorageAdmin.register(this);  // for app exit (tanja)



        // Katia & Iuliia 04.01
        // define new dbcontroller with parameter context
        StorageAdmin.DBCONTROLLER.setDBContext(getApplicationContext());
        // define buttons, editText and textView fields
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonCreateNewAccount = (Button) findViewById(R.id.buttonCreateNewAccount);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextVerifyPassword = (EditText) findViewById(R.id.editTextVerifyPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewVerifyPassword = (TextView) findViewById(R.id.textViewVerifyPassword);
        textViewUsernameMessage = (TextView) findViewById(R.id.textViewUsernameMessage);
        textViewVerifyPasswordMessage = (TextView) findViewById(R.id.textViewVerifyPasswordMessage);
        textViewInfo = (TextView) findViewById(R.id.textViewInfo);
        textViewEmailMessage = (TextView) findViewById(R.id.textViewEmailMessage);
        textViewPasswordMessage = (TextView) findViewById(R.id.textViewPasswordMessage);

        //get registerController Instance
        //registerController = RegisterControllerImpl.getRegisterController();
        registerController = (RegisterControllerImpl)StorageAdmin.REGISTERCONTROLLER;
        //set context
        registerController.setCurrentActivityContext(this);



        editTextUsername.addTextChangedListener(new TextWatcher() {
            /**
             * check after each input whether username already exists in database
             *
             * @param s
             */
            public void afterTextChanged(Editable s) {
                System.out.println("afterTextChanged event handler");
                //if(StorageAdmin.DBCONTROLLER.checkUsername(s.toString())){
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

        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            /**
             * check whether input password matches with specified password
             *
             * @param v
             * @param hasFocus
             */
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (registerController.checkPasswordIsTooShort(editTextPassword.getText().toString())) {
                        textViewPasswordMessage.setText("Password is too short!");
                        textViewPasswordMessage.setTextColor(Color.RED);
                    }
                    //else {
                    //    textViewPasswordMessage.setText("");
                    //}
                    else if (registerController.checkPasswordIsTooLong(editTextPassword.getText().toString())) {
                        textViewPasswordMessage.setText("Password is too long!");
                        textViewPasswordMessage.setTextColor(Color.RED);
                    } else {
                        textViewPasswordMessage.setText("");
                    }
                } else {
                    textViewVerifyPasswordMessage.setText("");
                }
            }
        });

        editTextVerifyPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            /**
             * check whether input password matches with specified password
             *
             * @param v
             * @param hasFocus
             */
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

        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            /**
             * check whether input password matches with specified password
             *
             * @param v
             * @param hasFocus
             */
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (registerController.checkEmailIsValid(editTextEmail.getText().toString())) {
                        textViewEmailMessage.setText("Enter a valid email address!");
                        textViewEmailMessage.setTextColor(Color.RED);
                    } else {
                        textViewEmailMessage.setText("");
                    }
                } else {
                    textViewEmailMessage.setText("");
                }
            }
        });



    buttonRegister.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        String username = editTextUsername.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String verifyPassword = editTextVerifyPassword.getText().toString();
        // validate username, password, verifyPassword and email inputs
        if (validateUserInputData(username, password, verifyPassword, email)) {
            String loggedIn = "1";
            User user = new User(username, email, password, loggedIn);
            System.out.println("User ------> " + user);
            if (StorageAdmin.REGISTERCONTROLLER.createUser(user)) {

                User currectUser = StorageAdmin.LOGINCONTROLLER.getUserFromTable(user.getName());
                StorageAdmin.newSession(currectUser);
                System.out.println(" IF Abfrage User ------>   true");
                startActivity(registerController.screenFlowMain());
                Toast.makeText(Login_Register_Activity.this, "Registration successfull!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Login_Register_Activity.this, "Database connection has failed!", Toast.LENGTH_LONG).show();
            }
        }
    }
    });
        buttonCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set all fields visible and login button invisible
                inCreateNewAccountMode = true;
                editTextEmail.setVisibility(View.VISIBLE);
                textViewEmail.setVisibility(View.VISIBLE);
                textViewUsernameMessage.setVisibility(View.VISIBLE);
                textViewVerifyPasswordMessage.setVisibility(View.VISIBLE);
                textViewPasswordMessage.setVisibility(View.VISIBLE);
                textViewEmailMessage.setVisibility(View.VISIBLE);
                editTextVerifyPassword.setVisibility(View.VISIBLE);
                textViewVerifyPassword.setVisibility(View.VISIBLE);
                buttonCreateNewAccount.setVisibility(View.INVISIBLE);
                buttonRegister.setVisibility(View.VISIBLE);
                buttonLogin.setVisibility(View.INVISIBLE);
                textViewInfo.setText("Create new Account");
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                System.out.println("LOGIN BUTTON CLICKED");
                System.out.println("username " + username);
                // check username
                //if(StorageAdmin.DBCONTROLLER.checkUsername(username))
                if(StorageAdmin.LOGINCONTROLLER.checkUsername(username))
                {
                    // check username matches password
                    if(StorageAdmin.LOGINCONTROLLER.checkPasswordByName(password, username)){

                        String name = username;
                        Toast.makeText(Login_Register_Activity.this, "Login successful!", Toast.LENGTH_LONG).show();
                        User user = StorageAdmin.LOGINCONTROLLER.getUserFromTable(name);
                        StorageAdmin.newSession(user);
                        //getUser(name)
                        startActivity(registerController.screenFlowMain());
                    }
                    else{
                        Toast.makeText(Login_Register_Activity.this, "Name and password don't match!", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                else{
                    Toast.makeText(Login_Register_Activity.this, "Username doesn't exist!", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }

    /**
     * validate inputs
     *
     * @param username
     * @param password
     * @param verifyPassword
     * @param email
     * @return true whether input data is correct
     */
    public boolean validateUserInputData(String username, String password, String verifyPassword, String email){

       /* if (registerController.checkUsername(username)) {
            return false;
        }
        if (!registerController.verifyPassword(password, verifyPassword)) {
            return false;
        }
        if (registerController.checkNameIsEmpty(username) == true ||
                registerController.checkPasswordIsEmpty(password) == true ||
                registerController.checkEmailIsEmpty(email) == true) {
            Toast.makeText(Login_Register_Activity.this, "All fields must be filled!", Toast.LENGTH_LONG).show();
            return false;
        }*/
        if (registerController.checkEmailIsValid(email) == true) {
            Toast.makeText(Login_Register_Activity.this, "Enter a valid email address!", Toast.LENGTH_LONG).show();
            return false;
        }
        /*
        if (registerController.checkPasswordIsTooShort(password) == true) {
            Toast.makeText(Login_Register_Activity.this, "Password is too short!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (registerController.checkPasswordIsTooLong(password) == true) {
            Toast.makeText(Login_Register_Activity.this, "Password is too long!", Toast.LENGTH_LONG).show();
            return false;
        }*/
        else
            return true;
    }

}
