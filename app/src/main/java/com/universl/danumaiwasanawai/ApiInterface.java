package com.universl.danumaiwasanawai;

import com.universl.danumaiwasanawai.model.Posts;
import com.universl.danumaiwasanawai.model.Quiz;
import com.universl.danumaiwasanawai.model.User;
import com.universl.danumaiwasanawai.model.UserResult;
import com.universl.danumaiwasanawai.quiz.QuestionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {


    @GET("articles/search/Awards")
    Call<List<Posts>> getpost();

    @GET("articles/search/{Awards}")
    Call<List<Posts>> getpostbycategory(@Path("Awards") String Awards);


    @GET("Quiz")
    Call<List<Quiz>> getallquiz();

    @GET("Quiz_mcq/{QuizId}")
    Call<List<QuestionModel>> getQuestion(@Path("QuizId") String QuizId);;


    @POST("Result")
    @FormUrlEncoded
    Call<List<QuestionModel>>submitQuizResults(
            @Field("Quiz_id_fk") String Quiz_id_fk,
            @Field("student_id_fk") String student_id_fk,
            @Field("result") String result,
            @Field("time") String time);


    @POST("Userapp")
    @FormUrlEncoded
    Call<List<User>>Insertuser(
            @Field("user_id") String user_id,
            @Field("user_name") String user_name,
            @Field("email") String email,
            @Field("image") String image);


    @POST("Result/check")
    @FormUrlEncoded
    Call<Void> checkuser(
            @Field("Quiz_id_fk") String Quiz_id_fk,
            @Field("student_id_fk") String student_id_fk);


    @GET("Result/{Quiz_id_fk}")
    Call<List<UserResult>> getresult(@Path("Quiz_id_fk") String Quiz_id_fk);;


    @GET("quiz/resultquiz")
    Call<List<Quiz>> getallquizresult();


}
