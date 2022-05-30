package com.universl.danumaiwasanawai.model;

public class Quiz {

    private String quiz_id;
    private String quiz_title;
    private String quiz_date;
    private String quiz_time_hours;
    private String quiz_time_minutes;
    private String quiz_status;

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

    private String insert_by;

    private String user_name;
    private String image;




    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuiz_title() {
        return quiz_title;
    }

    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }

    public String getQuiz_date() {
        return quiz_date;
    }

    public void setQuiz_date(String quiz_date) {
        this.quiz_date = quiz_date;
    }

    public String getQuiz_time_hours() {
        return quiz_time_hours;
    }

    public void setQuiz_time_hours(String quiz_time_hours) {
        this.quiz_time_hours = quiz_time_hours;
    }

    public String getQuiz_time_minutes() {
        return quiz_time_minutes;
    }

    public void setQuiz_time_minutes(String quiz_time_minutes) {
        this.quiz_time_minutes = quiz_time_minutes;
    }

    public String getQuiz_status() {
        return quiz_status;
    }

    public void setQuiz_status(String quiz_status) {
        this.quiz_status = quiz_status;
    }

    public String getInsert_by() {
        return insert_by;
    }

    public void setInsert_by(String insert_by) {
        this.insert_by = insert_by;
    }

    public Quiz(String quiz_id, String article_title) {
        this.quiz_id = quiz_id;
        this.quiz_title = article_title;
    }

    public String alldata()
    {
     String data ="quiz_id :-"+quiz_id+"\n"+
             "quiz_title:-"+quiz_title+"\n"+
             "quiz_date:-"+quiz_date+"\n"+
             "quiz_time_hours:-"+quiz_time_hours+"\n"+
             "quiz_time_minutes:-"+quiz_time_minutes+"\n"+
             "quiz_status:-"+quiz_status+"\n"+
             "insert_by:-"+insert_by +"\n" +"\n" ;
     return data ;
    }
}

