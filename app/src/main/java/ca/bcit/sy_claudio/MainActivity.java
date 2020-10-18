package ca.bcit.sy_claudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#595359\">" + getString(R.string.app_name) + "</font>"));
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

    public void showToast(View view) {
        Context context = getApplicationContext();
        CharSequence text = getString(R.string.about_info);
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}