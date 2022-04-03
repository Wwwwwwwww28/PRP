package com.example.worldcinema;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.Service.ApiHandler;
import com.example.worldcinema.Service.IApi;
import com.example.worldcinema.Service.Models.RegistrationBody;
import com.example.worldcinema.Service.Models.RegistrationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    IApi iApi = ApiHandler.getInstance().getService();

    EditText firstname, lastname, email, password, repeatpassword;

    String fname;
    String lname;
    String mail;
    String pass;
    String rpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstname = findViewById(R.id.etFirstName);
        lastname = findViewById(R.id.etLastName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        repeatpassword = findViewById(R.id.etRepeatPassword);

        findViewById(R.id.btnReg).setOnClickListener(view -> {
            doRegister();
        });
    }

    public RegistrationBody GetParametrs(){
        fname = firstname.getText().toString();
        lname = lastname.getText().toString();
        mail = email.getText().toString();
        pass = password.getText().toString();
        rpass = repeatpassword.getText().toString();
        RegistrationBody rb = new RegistrationBody();
        rb.setFirstname(fname);
        rb.setLastname(lname);
        rb.setEmail(mail);
        rb.setPassword(pass);
        Log.e(TAG, rb.getEmail() + "" + rb.getPassword());
        return rb;

    }
    public void doRegister() {
        AsyncTask.execute(()->{
            iApi.register(GetParametrs()).enqueue(new Callback<RegistrationResponse>() {//вызываем метод регистрации
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else if(response.code() == 201){
                        Toast.makeText(getApplicationContext(), "не совсем OK", Toast.LENGTH_LONG);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Совсем не ок" + response.code(), Toast.LENGTH_LONG);
                    }
                }
                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG);
                }
            });
        });
    }
}