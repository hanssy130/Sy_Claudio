package ca.bcit.sy_claudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ResultTitlesActivity extends AppCompatActivity {
    private String TAG = ResultTitlesActivity.class.getSimpleName();
    private ListView lv;
    // URL to get contacts JSON
    private static String SERVICE_URL = "https://newsapi.org/v2/everything?q=bitcoin&from=2020-10-12&sortBy=publishedAt&apiKey=053d3857d0c44256b466b2e494de3d96";
    private ArrayList<Results> resultsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_titles);

        Intent intent = getIntent();
        String searchInput = intent.getStringExtra("input");
        System.out.println(searchInput);

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
                // this step is needed to wrap the JSON array inside
                // a JSON object that looks like this {"toons": .... }
                jsonStr = "{\"results\":" + jsonStr + "}";
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