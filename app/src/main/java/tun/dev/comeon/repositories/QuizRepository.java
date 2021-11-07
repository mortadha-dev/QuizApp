package tun.dev.comeon.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

import tun.dev.comeon.DAO.QuizDao;
import tun.dev.comeon.database.MyDatabase;
import tun.dev.comeon.entities.Quiz;

public class QuizRepository {

    //call the dao
    private QuizDao quizDao;

    private LiveData<List<Quiz>> listQuiz;

    public QuizRepository(Application application){
        MyDatabase database = MyDatabase.getInstance(application);
        quizDao =database.quizDAO(); //call the abstract method in the database
        listQuiz = quizDao.getAllQuiz();
    }

    //operations :
    public LiveData<List<Quiz>> getAllQuizs(String category){
        listQuiz= quizDao.getQuizByCategory(category);
        return listQuiz;
    }


}
