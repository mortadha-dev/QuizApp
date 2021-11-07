package tun.dev.comeon.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import tun.dev.comeon.repositories.QuizRepository;
import tun.dev.comeon.entities.Quiz;

public class QuizViewModel extends AndroidViewModel {


    private QuizRepository quizRepository;
    private LiveData<List<Quiz>> allquizs;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        quizRepository = new QuizRepository(application);
       // allquizs = quizRepository.getAllQuizs();
    }

    public LiveData<List<Quiz>> getAllQuizByCategory(String category) {
        allquizs = quizRepository.getAllQuizs(category);
        return allquizs;
    }
}
