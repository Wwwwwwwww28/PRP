package com.example.worldcinema.Service;

import com.example.worldcinema.Service.Models.ChatMessagesResponse;
import com.example.worldcinema.Service.Models.LoginBody;
import com.example.worldcinema.Service.Models.LoginResponce;
import com.example.worldcinema.Service.Models.MovieResponse;
import com.example.worldcinema.Service.Models.ProfileResponse;
import com.example.worldcinema.Service.Models.RegistrationBody;
import com.example.worldcinema.Service.Models.RegistrationResponse;
import com.example.worldcinema.Service.Models.SendMessageBody;
import com.example.worldcinema.Service.Models.UserChatsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IApi {
    int chatID = 0;
    @POST("/auth/register") //метод регистрации
    Call<RegistrationResponse> register(@Body RegistrationBody registrationBody);//передаем тело запроса
    @POST("/auth/login")
    Call<LoginResponce> login(@Body LoginBody LoginBody);
    @GET("/movies?filter=inTrend")
    Call<List<MovieResponse>> newMovie();
    @GET("/user")
    Call<List<ProfileResponse>> getUser(@Header("Authorization") String token);//используем авторизацию, отправляем токен пользователя в заголовке запроса
    @GET("/chats/{movieId}")
    Call<List<UserChatsResponse>> getUserChats(@Header("Authorization") String token, @Path("movieId") String movieID);//Path позволяет динамически менять строку запроса.
    @GET("/user/chats")
    Call<List<UserChatsResponse>> getUserChats(@Header("Authorization") String token);
    @GET("/chats/{chatId}/messages")
    Call<List<ChatMessagesResponse>> getChatMessages(@Header("Authorization") String token, @Path("chatId") String chatID);
    @POST("/chats/{chatId}/messages")
    Call<ChatMessagesResponse> sendMessage(@Header("Authorization") String token,@Path("chatId") String chatID, @Body SendMessageBody sendMessageBody);//реализация запроса с авторизацией, передачей параметров, передачей тела
}
