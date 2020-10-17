package ca.bcit.sy_claudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.inputtext);
    }

    public void search(View view) {
        String toSearch = input.getText().toString();
        if (TextUtils.isEmpty(toSearch)) {
            input.setError("Cannot leave as blank.");
        } else {
            Intent intent = new Intent(MainActivity.this, ResultTitlesActivity.class);
            intent.putExtra("input", toSearch);
            startActivity(intent);
        }
    }
}