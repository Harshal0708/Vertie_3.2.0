package com.vertie.javacode.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionsObj {
    @SerializedName("questionId")
    @Expose
    private String questionId;
    @SerializedName("questionType")
    @Expose
    private String questionType;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("filePath")
    @Expose
    private String filePath;

    public QuestionsObj(String questionId, String questionType, String answer, String unit, String filePath) {
        this.questionId = questionId;
        this.questionType = questionType;
        this.answer = answer;
        this.unit = unit;
        this.filePath = filePath;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "{" +
                "questionId:'" + questionId + '\'' +
                ", questionType:'" + questionType + '\'' +
                ", answer:'" + answer + '\'' +
                ", unit:'" + unit + '\'' +
                ", filePath:'" + filePath + '\'' +
                '}';
    }
}
