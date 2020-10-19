package ca.bcit.sy_claudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ResultTitlesActivity extends AppCompatActivity {
    private String TAG = ResultTitlesActivity.class.getSimpleName();
    private ListView lv;
    // URL to get contacts JSON
    private static String SERVICE_URL = "https://newsapi.org/v2/everything?q=yeet&from=2020-10-12&sortBy=publishedAt&apiKey=053d3857d0c44256b466b2e494de3d96";
    private ArrayList<Results> resultsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_titles);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#595359\">" + getString(R.string.app_name) + "</font>"));

        // get intent and the keyword user typed in
        Intent intent = getIntent();
        String searchInput = intent.getStringExtra("input");
        // for testing and debugging purposes
        System.out.println(searchInput);

        // set date (inserted in SERVICE_URL) to one week behind current time
        // Date code from https://stackoverflow.com/questions/11272431/get-date-of-past-7days-from-current-in-android
        final LocalDate date = LocalDate.now();
        final LocalDate dateMinus7Days = date.minusDays(7);
        final String formattedDate = dateMinus7Days.format(DateTimeFormatter.ISO_LOCAL_DATE);
        SERVICE_URL = "https://newsapi.org/v2/everything?q=" + searchInput + "&from=" + formattedDate + "&sortBy=publishedAt&apiKey=053d3857d0c44256b466b2e494de3d96";
        // for testing and debugging purposes
        System.out.println(SERVICE_URL);

        // instantiate arraylist and listview
        resultsList = new ArrayList<Results>();
        lv = findViewById(R.id.resultList);
        new GetResults().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetResults extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)  {
            HttpHandler sh = new HttpHandler();
            String jsonStr = null;

            // Making a request to URL and getting response
            jsonStr = sh.makeServiceCall(SERVICE_URL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                Log.d(TAG, "Json: " + jsonStr);
                Gson gson = new Gson();
                BaseResults baseResults = gson.fromJson(jsonStr, BaseResults.class);
                resultsList = baseResults.getResults();
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplication(),
                                "Couldn't get json from server. Check LogCat for possible errors.", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);

            ResultsAdapter adapter = new ResultsAdapter(ResultTitlesActivity.this, resultsList);

            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
        }
    }
}