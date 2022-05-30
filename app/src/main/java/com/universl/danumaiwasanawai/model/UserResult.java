package com.universl.danumaiwasanawai.model;

public class UserResult {


    private String Quiz_result_ID;
    private String Quiz_id_fk;
    private String student_id_fk;
    private String result;
    private String time;
    private String user_name;
    private String image;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserResult(String quiz_id_fk, String student_id_fk) {

        this.Quiz_id_fk = quiz_id_fk;
        this.student_id_fk = student_id_fk;

    }

    public String getQuiz_result_ID() {
        return Quiz_result_ID;
    }

    public void setQuiz_result_ID(String quiz_result_ID) {
        Quiz_result_ID = quiz_result_ID;
    }

    public String getQuiz_id_fk() {
        return Quiz_id_fk;
    }

    public void setQuiz_id_fk(String quiz_id_fk) {
        Quiz_id_fk = quiz_id_fk;
    }

    public String getStudent_id_fk() {
        return student_id_fk;
    }

    public void setStudent_id_fk(String student_id_fk) {
        this.student_id_fk = student_id_fk;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
