package com.example.worldcinema;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.Service.ApiHandler;
import com.example.worldcinema.Service.IApi;
import com.example.worldcinema.Service.Models.LoginBody;
import com.example.worldcinema.Service.Models.LoginResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Login";
    EditText etLogin, etPass;
    String login, pass;
    IApi IApi = ApiHandler.getInstance().getService(); //инициализируем интерфейс api

    SharedPreferences sp;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etLogin = findViewById(R.id.etlogin);
        etPass = findViewById(R.id.etPass);

        findViewById(R.id.btnLogin).setOnClickListener(view -> { //привязываем к кнопке обработчик на нажатие
            doLogin();
        });
    }
    public LoginBody GetParametrs(){ //считываем, вводимые пользователем данные
        login = etLogin.getText().toString();
        pass = etPass.getText().toString();
        LoginBody lb = new LoginBody(); //тело запроса
        lb.setEmail(login);
        lb.setPassword(pass);
        return lb;

    }


    public void doLogin() { //авторизация
        AsyncTask.execute(()->{ //запускаем в отдельном потоке
            IApi.login(GetParametrs()).enqueue(new Callback<LoginResponce>() { //вызываем метод login (указан в интерфейсе), передаем тело запроса
                @Override
                public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) { //в случае успешного подключения
                    if(response.isSuccessful()){
                        sp = getApplicationContext().getSharedPreferences("settings", Context.MODE_PRIVATE); //создаем файл с настройками приложения
                        SharedPreferences.Editor editor = sp.edit(); //создаем объект для редактирования настроек
                        editor.putString("token", response.body().getToken()); //записываем токен в настройки
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(), Main.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Ошибка: " + response.code(), Toast.LENGTH_LONG);
                    }
                }
                @Override
                public void onFailure(Call<LoginResponce> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG);
                }
            });
        });
    }

    public void goRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }
}