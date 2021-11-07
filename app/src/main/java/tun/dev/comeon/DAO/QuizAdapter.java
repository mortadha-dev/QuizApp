package tun.dev.comeon.DAO;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tun.dev.comeon.R;
import tun.dev.comeon.activities.InscriptionActivity;
import tun.dev.comeon.entities.Quiz;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder>{

    private List<Quiz> quizs = new ArrayList<>() ;


    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_list_item, parent, false);
        return new QuizViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull  QuizViewHolder holder, int position) {
        Quiz currentQuiz = quizs.get(position);
        holder.textViewQuestion.setText(currentQuiz.getQuestion());
        holder.ButtonFirstResponse.setText(currentQuiz.getRone());
        holder.ButtonSecondResponse.setText(currentQuiz.getRtwo());

    }

    public  void setQuizs(List<Quiz> quizs)
    {
        this.quizs=quizs ;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return quizs.size();
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewQuestion;
        public Button ButtonFirstResponse;
        public Button ButtonSecondResponse;

        public QuizViewHolder(@NonNull  View itemView) {
            super(itemView);
            textViewQuestion = itemView.findViewById(R.id.mortadhaquestion);
            ButtonFirstResponse = itemView.findViewById(R.id.mortadha1);
            ButtonSecondResponse = itemView.findViewById(R.id.mortadha2);

            ButtonFirstResponse.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("mmmmmm");
                        }
                    });
        }





    }


}
