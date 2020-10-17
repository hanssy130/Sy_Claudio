package ca.bcit.sy_claudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultTitlesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_titles);

        Intent intent = getIntent();
        String searchInput = intent.getStringExtra("input");
        System.out.println(searchInput);

    }
}