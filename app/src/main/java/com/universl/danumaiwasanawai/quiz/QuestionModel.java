package com.universl.danumaiwasanawai.quiz;

public class QuestionModel {

    private String quiz_question,quiz_answer_1,quiz_answer_2, quiz_answer_3,quiz_answer_4,Quiz_id_fk,student_id_fk,result,time;
    private int correctanswer;


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

    public String getOption4() {
        return quiz_answer_4;
    }

    public void setOption4(String option4) {
        this.quiz_answer_4 = option4;
    }

    public QuestionModel(String question, String option1, String option2, String option3, String option4, int correctAnsNo) {
        this.quiz_question = question;
        this.quiz_answer_1 = option1;
        this.quiz_answer_2 = option2;
        this.quiz_answer_3 = option3;
        this.quiz_answer_4 = option4;
        this.correctanswer = correctAnsNo;
    }

    public String getQuestion() {
        return quiz_question;
    }

    public void setQuestion(String question) {
        this.quiz_question = question;
    }

    public String getOption1() {
        return quiz_answer_1;
    }

    public void setOption1(String option1) {
        this.quiz_answer_1 = option1;
    }

    public String getOption2() {
        return quiz_answer_2;
    }



    public void setOption2(String option2) {
        this.quiz_answer_2 = option2;
    }

    public String getOption3() {
        return quiz_answer_3;
    }

    public void setOption3(String option3) {
        this.quiz_answer_3 = option3;
    }

    public int getCorrectAnsNo() {
        return correctanswer;
    }

    public void setCorrectAnsNo(int correctAnsNo) {
        this.correctanswer = correctAnsNo;
    }

    public String alldata() {
        return quiz_answer_4;
    }
}
