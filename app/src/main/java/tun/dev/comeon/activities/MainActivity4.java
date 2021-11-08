package tun.dev.comeon.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import tun.dev.comeon.R;
import tun.dev.comeon.ViewModel.QuizViewModel;
import tun.dev.comeon.entities.Quiz;

public class MainActivity4 extends AppCompatActivity {

    TextView txtQuestion;
    TextView textViewScore, textViewQuestionCount, textViewCountDownTimer;
    RadioButton rb1, rb2, rb3, rb4;
    RadioGroup rbGroup;
    Button buttonNext;
    String categoryValue = "";


    private int questionTotalCount;
    boolean answerd = false;
    private int correctAns = 0;
    private int wrongAns = 0;
    private int score = 0;
    private int questionCounter = 0;
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private long timeLeftinMillis;


    List<Quiz> quesList;
    Quiz currentQ;
    private QuizViewModel questionViewModel;

    private WrongDialog wrongDialog;
    private CorrectDialog correctDialog;
    private CountDownTimer countDownTimer;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        DefiningViewElements();


        wrongDialog = new WrongDialog(this);
        correctDialog = new CorrectDialog(this);

        Intent mainActivity2Intent = getIntent();
        categoryValue = mainActivity2Intent.getStringExtra("Category");
        questionViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        questionViewModel.getAllQuizByCategory(categoryValue).observe(this, new Observer<List<Quiz>>() {
            @Override
            public void onChanged(@Nullable List<Quiz> questions) {
                Toast.makeText(MainActivity4.this, "Here We Go :)", Toast.LENGTH_SHORT).show();
                getAllContent(questions);

            }
        });
    }


    void DefiningViewElements() {
        textViewCountDownTimer = findViewById(R.id.txtTimer);
        textViewScore = findViewById(R.id.txtScore);
        textViewQuestionCount = findViewById(R.id.txtTotalQuestion);
        txtQuestion = findViewById(R.id.txtQuetsionContainer);
        rbGroup = findViewById(R.id.raido_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonNext = findViewById(R.id.button_Next);

    }


    private void getAllContent(List<Quiz> questions) {
//fetch question by categories
        Intent i = getIntent();
        int level = i.getIntExtra("level", 1);
        if (level == 1) {
            assert questions != null;
            quesList = questions.subList(0, 5);
        } else if (level == 2) {
            assert questions != null;
            quesList = questions.subList(0, 10);
        } else if (level == 3) {
            assert questions != null;
            quesList = questions.subList(0, 15);
        } else {
            Toast.makeText(MainActivity4.this, "Something wrong", Toast.LENGTH_SHORT).show();
        }

        startQuiz();

    }


    public void setQuestionView() {
        rbGroup.clearCheck();
        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_a));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_b));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_c));
        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_d));

        rb1.setTextColor(Color.BLACK);
        rb2.setTextColor(Color.BLACK);
        rb3.setTextColor(Color.BLACK);
        rb4.setTextColor(Color.BLACK);

        questionTotalCount = quesList.size();

        if (questionCounter < questionTotalCount) {
            currentQ = quesList.get(questionCounter);

            textViewQuestionCount.setText("Question: " + questionCounter + "/" + (questionTotalCount));
            txtQuestion.setText(currentQ.getQuestion());
            timeLeftinMillis = COUNTDOWN_IN_MILLIS;
            rb1.setText(currentQ.getRone());
            rb2.setText(currentQ.getRtwo());
            rb3.setText(currentQ.getRthree());
            rb4.setText(currentQ.getRfour());
            buttonNext.setText("Confirm");
            answerd = false;
            questionCounter++;

            startCountDown();

        } else {

            Toast.makeText(this, "Quiz Finished", Toast.LENGTH_SHORT).show();
            rb1.setClickable(false);
            rb2.setClickable(false);
            rb3.setClickable(false);
            rb4.setClickable(false);
            buttonNext.setClickable(false);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    resultData();
                }
            }, 2000);
        }
    }


    private void startQuiz() {
        setQuestionView();
        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.radio_button1:
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected_option_a));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_b));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_c));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_d));
                        break;

                    case R.id.radio_button2:
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_a));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected_option_b));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_c));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_d));
                        break;

                    case R.id.radio_button3:
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_a));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_b));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected_option_c));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_d));
                        break;

                    case R.id.radio_button4:
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_a));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_b));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_option_c));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected_option_d));


                        break;
                }
            }
        });


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answerd) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        quizOpeartion();
                    } else {
                        Toast.makeText(MainActivity4.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void quizOpeartion() {
        answerd = true;
        countDownTimer.cancel();
        RadioButton rbselected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbselected) + 1;
        checkSolution(answerNr, rbselected);
    }

    private void checkSolution(int answerNr, RadioButton rbselected) {
        switch (currentQ.getCorrectAnswer()) {

            case 1:
                if (currentQ.getCorrectAnswer() == answerNr) {
                    rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb1.setTextColor(Color.WHITE);
                    correctAns++;
                    score += 10;  // score = score + 10
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score, this);
                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;
                    final String correctAnswer = (String) rb1.getText();
                    wrongDialog.WrongDialog(correctAnswer, this);
                }
                break;


            case 2:
                if (currentQ.getCorrectAnswer() == answerNr) {
                    rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb2.setTextColor(Color.WHITE);
                    correctAns++;
                    score += 10;  // score = score + 10
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score, this);
                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;
                    final String correctAnswer = (String) rb2.getText();
                    wrongDialog.WrongDialog(correctAnswer, this);
                }
                break;


            case 3:
                if (currentQ.getCorrectAnswer() == answerNr) {
                    rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb3.setTextColor(Color.WHITE);
                    correctAns++;
                    score += 10;  // score = score + 10
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score, this);
                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;
                    final String correctAnswer = (String) rb3.getText();
                    wrongDialog.WrongDialog(correctAnswer, this);
                }
                break;


            case 4:
                if (currentQ.getCorrectAnswer() == answerNr) {
                    rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb4.setTextColor(Color.WHITE);
                    correctAns++;
                    score += 10;  // score = score + 10
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score, this);

                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;
                    final String correctAnswer = (String) rb4.getText();
                    wrongDialog.WrongDialog(correctAnswer, this);

                }
                break;
        }
        if (questionCounter == questionTotalCount) {
            buttonNext.setText("Confirm and Finish");
        }

    }

    private void changetoIncorrectColor(RadioButton rbselected) {
        rbselected.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_wrong));
        rbselected.setTextColor(Color.WHITE);
    }


    // The timer code


    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftinMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftinMillis = 0;
                updateCountDownText();
            }
        }.start();
    }

    private void updateCountDownText() {

        int minutes = (int) (timeLeftinMillis / 1000) / 60;
        int seconds = (int) (timeLeftinMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDownTimer.setText(timeFormatted);
        if (timeLeftinMillis < 10000) {
            textViewCountDownTimer.setTextColor(Color.RED);

        } else {
            textViewCountDownTimer.setTextColor(ContextCompat.getColor(this, R.color.black));
        }
        if (timeLeftinMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            }, 2000);
        }
    }

    private void resultData() {
        Intent resultofQuiz = new Intent(MainActivity4.this, ResultActivity.class);
        resultofQuiz.putExtra("UserScore", score);
        resultofQuiz.putExtra("TotalQuizQuestions", (questionTotalCount));
        resultofQuiz.putExtra("CorrectQuestions", correctAns);
        resultofQuiz.putExtra("WrongQuestions", wrongAns);
        startActivity(resultofQuiz);
    }


}