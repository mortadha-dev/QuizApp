package tun.dev.comeon.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Quiz {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String question;
    @ColumnInfo
    private String rone;
    @ColumnInfo
    private String rtwo;
    @ColumnInfo
    private String rthree;
    @ColumnInfo
    private String rfour;
    @ColumnInfo
    private int correctAnswer;
    @ColumnInfo
    public int userAnswer;
    @ColumnInfo(name = "category")
    public String category;


    public Quiz() {
    }

    public Quiz(String question, String rone, String rtwo, String rthree, int correctAnswer,String category) {
        this.question = question;
        this.rone = rone;
        this.rtwo = rtwo;
        this.rthree = rthree;
        this.correctAnswer = correctAnswer;
        this.category= category;
    }

    public Quiz(String question, String rone, String rtwo) {
        this.question = question;
        this.rone = rone;
        this.rtwo = rtwo;
    }
    public Quiz(String question, String rone, String rtwo, String rthree,String rfour, int correctAnswer,String category) {
        this.question = question;
        this.rone = rone;
        this.rtwo = rtwo;
        this.rthree = rthree;
        this.rfour=rfour;
        this.correctAnswer = correctAnswer;
        this.category= category;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRone() {
        return rone;
    }

    public void setRone(String rone) {
        this.rone = rone;
    }

    public String getRtwo() {
        return rtwo;
    }

    public void setRtwo(String rtwo) {
        this.rtwo = rtwo;
    }

    public String getRthree() {
        return rthree;
    }

    public void setRthree(String rthree) {
        this.rthree = rthree;
    }

    public String getRfour() {
        return rfour;
    }

    public void setRfour(String rfour) {
        this.rfour = rfour;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }
}
