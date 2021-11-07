package tun.dev.comeon.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tun.dev.comeon.DAO.QuizAdapter;
import tun.dev.comeon.DAO.QuizDao;
import tun.dev.comeon.ViewModel.QuizViewModel;
import tun.dev.comeon.R;
import tun.dev.comeon.database.MyDatabase;
import tun.dev.comeon.entities.Quiz;

public class MainActivity3 extends AppCompatActivity {
   /* private QuizViewModel quizViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        quizViewModel.getAllquizs().observe(this, new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                // update recey

            }
        });

    }

    /*
    private List<Quiz> quizList;
    private TextView questionView;
    private ProgressBar progressBar;
    private RadioGroup radioGroup;
    private RadioButton radioButtonOne;
    private RadioButton radioButtonTwo;
    private RadioButton radioButtonThree;
    private Button buttonNext;
    int indexCurrentQuestion;
    TextView textTime;
    CountDownTimer countDownTimer;
     MyDatabase db;
     QuizDao quizDao;
    QuizViewModel quizViewModel ;
    private QuizAdapter quizAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        questionView = findViewById(R.id.question1);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(50);
        radioGroup = findViewById(R.id.radioQuiz);
        radioButtonOne = findViewById(R.id.rone);
        radioButtonTwo = findViewById(R.id.rtwo);
        radioButtonThree = findViewById(R.id.rthree);
        textTime = findViewById(R.id.textTime);



        db = MyDatabase.getInstance(this);
        db = MyDatabase.getInstance(this);
       // this.quizList = db.quizDAO().getAllQuiz();















        radioButtonOne.setOnClickListener(view -> {
            ((RadioButton) view).setChecked(true);
            quizList.get(indexCurrentQuestion).setUserAnswer(1);
        });

        radioButtonTwo.setOnClickListener(view -> {
            ((RadioButton) view).setChecked(true);
            quizList.get(indexCurrentQuestion).setUserAnswer(2);
        });

        radioButtonThree.setOnClickListener(view -> {
            ((RadioButton) view).setChecked(true);
            quizList.get(indexCurrentQuestion).setUserAnswer(3);
        });
        buttonNext = findViewById(R.id.buttonNext);

        Intent intent = getIntent();
        int level = intent.getIntExtra("level", 1);


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       /* if (level == 1) {
            assert list != null;
            quizList = list.subList(0, 2);
        } else if (level == 2) {
            assert list != null;
            quizList = list.subList(5, 10);
        } else {
            assert list != null;
            quizList = list.subList(10, 15);}*/

/*
        indexCurrentQuestion = 0;
       Quiz currentQuestion = quizList.get(indexCurrentQuestion);
        System.out.println(currentQuestion);
        currentQuestionView(currentQuestion);

        buttonNext.setOnClickListener(v -> {
            System.out.println("hello im button next hhhhhhhhhhhhhhhhhhhhhhhhh");
            indexCurrentQuestion++;
            next(v);

        });

    startTimer();

    // When user submit quiz, stop time and start Solution Activity
         Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(v -> {
        stopTimer();
        Intent i = new Intent(MainActivity3.this, LevelsClass.class);
        i.putExtra("score", getScore());
        // Change List to ArrayList to accommodate subList
        ArrayList<Quiz> list = new ArrayList<>(quizList);
        i.putExtra("quizList", list);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    });
}

    public void startTimer() {
    int seconds = 50;

        textTime.setText(String.valueOf(seconds));
        countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textTime.setText(String.valueOf((int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(MainActivity3.this, PopUpTrue.class);
               i.putExtra("score", getScore());
                // Change List to ArrayList to accommodate subList
                startActivity(i);
            }}.start(); }

    public void stopTimer() {
        countDownTimer.cancel();
    }

    public void next(View view) {
          //int current = this.indexCurrentQuestion;
          //List<Quiz> list = this.quizList;
                // item clicked
                if(indexCurrentQuestion != (quizList.size() + 1)) {
                    System.out.println("before ++ "+indexCurrentQuestion);

                    System.out.println("After ++ "+indexCurrentQuestion);
                    if(indexCurrentQuestion == (quizList.size() - 1)) buttonNext.setEnabled(false);
                    Quiz currentQuestion = quizList.get(indexCurrentQuestion);
                    currentQuestionView(currentQuestion);
                    radioGroup = findViewById(R.id.radioQuiz);
                    if(currentQuestion.getUserAnswer() == 0) radioGroup.clearCheck();
                    else {
                        switch (currentQuestion.getUserAnswer()) {
                            case 1: {
                                radioGroup.check(R.id.rone);
                                break;
                            }
                            case 2: {
                                radioGroup.check(R.id.rtwo);
                                break;
                            }
                            case 3: {
                                radioGroup.check(R.id.rthree);
                                break;
                            }

                        }
                    }
                }
            }


    public void currentQuestionView(Quiz currentQuestion) {
        this.questionView.setText(String.format("%s. %s", this.indexCurrentQuestion, currentQuestion.getQuestion()));
        this.radioButtonOne.setText(currentQuestion.getRone());
        this.radioButtonTwo.setText(currentQuestion.getRtwo());
        this.radioButtonThree.setText(currentQuestion.getRthree());}

    public int getScore() {
        int score = 0;
        for (int i = 0; i < this.quizList.size(); i++) {
            if (this.quizList.get(i).getUserAnswer() == this.quizList.get(i).getCorrectAnswer())
                score++;
        }
        return score;
    }


        /*Button button1 = findViewById(R.id.reponse1);
        Button button2 = findViewById(R.id.reponse2);
        Button button3 = findViewById(R.id.reponse3);
        button1.setText("Lyon");
        button2.setText("Bordeaux");
        button3.setText("Grenoble");
        button1.setOnClickListener(mAddListener);
        button2.setOnClickListener(mAddListener);
        button3.setOnClickListener(mAddListener);

    }

    public void onBtnClick(View view) {

        Button button = findViewById(R.id.buttonToList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }




    private View.OnClickListener mAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button1 = findViewById(R.id.reponse1);
            Button button2 = findViewById(R.id.reponse2);
            Button button3 = findViewById(R.id.reponse3);
            button1.setText("Lyon");
            button2.setText("Bordeaux");
            button3.setText("Grenoble");
            String trueone = "Lyon";
            TextView textView = findViewById(R.id.score) ;
            textView.setText("0");
            if (button1.isPressed() && button1.getText() == trueone) {
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity3.this, PopUpTrue.class));
                        int ff= 1 ;
                        String aa= String.valueOf(ff) ;
                        TextView textView = findViewById(R.id.score) ;
                        textView.setText(aa);
                        ProgressBar progressBar = findViewById(R.id.progressBar) ;
                        progressBar.setProgress(50);

                    }
                });
            } else if (button2.isPressed() && button2.getText() != trueone) {
                {
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(MainActivity3.this, PopUpFalse.class));

                        }
                    });
                }
            } else if (button3.isPressed() && button3.getText() != trueone) {
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity3.this, PopUpFalse.class));

                    }
                });
            } else {
                System.out.println("gouuum brrr");
            }

        }
    };
*/


       /* public void next (View view){

            Intent i = new Intent(this, MainActivity4.class);
            startActivity(i);
        }*/
    }



