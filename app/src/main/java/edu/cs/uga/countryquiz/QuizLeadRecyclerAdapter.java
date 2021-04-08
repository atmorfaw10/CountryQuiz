package edu.cs.uga.countryquiz;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * This is an adapter class for the RecyclerView to show all quizzes.
 */
public class QuizLeadRecyclerAdapter extends RecyclerView.Adapter<QuizLeadRecyclerAdapter.QuizLeadHolder> {

    public static final String DEBUG_TAG = "QuizLeadRecyclerAdapter";

    private List<Quiz> countryQuizList;

    public QuizLeadRecyclerAdapter( List<Quiz> countryQuizList)
    {
        this.countryQuizList =  countryQuizList;
    }

    // The adapter must have a view holder class to "hold" one item to show.
    class QuizLeadHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView quizScore;

        public QuizLeadHolder (View itemView)
        {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.text_view_date);
            quizScore = (TextView) itemView.findViewById(R.id.text_view_quiz_results);
        }
    }

    @Override
    public QuizLeadHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_quiz, parent, false);
        return new QuizLeadHolder(view);
    }

    /**
     * This method fills in the values of a holder to show a quiz
     * The position paramter indicates the position on the list of quiz list
     */

    @Override
    public void onBindViewHolder(QuizLeadHolder holder, int position)
    {
        Quiz quiz = countryQuizList.get(position);

        Log.d(DEBUG_TAG, "onBindViewHolder: " + quiz);


           holder.date.setText("Date: " + quiz.getDate() + " ");
           holder.quizScore.setText("Your Score: " + quiz.getResult() + "/6");


    }

    @Override
    public int getItemCount()
    {
        return countryQuizList.size();
    }

}
