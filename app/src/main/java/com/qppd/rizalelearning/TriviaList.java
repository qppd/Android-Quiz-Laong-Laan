package com.qppd.rizalelearning;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class TriviaList extends ArrayAdapter<Trivia> {

    private Activity context;
    private List<Trivia> triviaList;

    public TriviaList(Activity context, List<Trivia> triviaList){
        super(context, R.layout.activity_trivia, triviaList);
        this.context = context;
        this.triviaList = triviaList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View ListViewItem = inflater.inflate(R.layout.layout_list_trivia, null, true);

        //TextView titleText = (TextView)ListViewItem.findViewById(R.id.title);
        TextView triviaText = (TextView)ListViewItem.findViewById(R.id.trivia);
        Trivia trivia = triviaList.get(position);

        //titleText.setText(trivia.getTitle());
        triviaText.setText(trivia.getTrivia());

        return ListViewItem;

    }

}
