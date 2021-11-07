package tun.dev.comeon.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tun.dev.comeon.entities.Quiz;

@Dao
public interface QuizDao {

    @Insert
    void addQuiz(Quiz quiz);

    @Query("SELECT * FROM quiz ")
    LiveData<List<Quiz>> getAllQuiz();

    @Query("SELECT * from quiz q where q.category = :category")
    LiveData<List<Quiz>> getQuizByCategory(String category);

}
