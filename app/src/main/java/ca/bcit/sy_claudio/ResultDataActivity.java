package ca.bcit.sy_claudio;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.Html;
        import android.widget.ImageView;
        import android.widget.TextView;

public class ResultDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_data);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#595359\">" + getString(R.string.app_name) + "</font>"));

        // get intent and store the json string values passed in
        Intent intent = getIntent();
        String source = intent.getStringExtra("source");
        String author = intent.getStringExtra("author");
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String url = intent.getStringExtra("url");
        String urltoimage = intent.getStringExtra("urltoimage");
        String publishedate = intent.getStringExtra("publishedat");
        String content = intent.getStringExtra("content");

        // instantiate TextView objects and link them to respective layout elements
        TextView tvSource = findViewById(R.id.source);
        TextView tvAuthor = findViewById(R.id.author);
        TextView tvTitle = findViewById(R.id.title);
        TextView tvDescription = findViewById(R.id.description);
        TextView tvUrl = findViewById(R.id.url);
        ImageView imageThumbnail = findViewById(R.id.smallThumbnail);
        // if thumbnail is not null
        if (urltoimage != null) {
            // download and set that image to ImageView
            new ImageDownloaderTask(imageThumbnail).execute(urltoimage);
        }
        TextView tvPublishedAt = findViewById(R.id.publishedAt);
        TextView tvContent = findViewById(R.id.content);

        // set text for respective layout elements
        tvAuthor.setText("by " + author);
        // emojis are appended and prepended to the title
        tvTitle.setText("\uD83D\uDD25\uD83D\uDCAF\uD83D\uDE2E " + title +" \uD83D\uDE2E\uD83D\uDCAF\uD83D\uDD25");
        tvDescription.setText(description);
        tvSource.setText("Source: " + source);
        tvUrl.setText(url);
        tvPublishedAt.setText("Published at " + publishedate);
        tvContent.setText(content);
    }
}