package ca.bcit.sy_claudio;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.ImageView;
        import android.widget.TextView;

public class ResultDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_data);

        Intent intent = getIntent();
        String source = intent.getStringExtra("source");
        System.out.println("Source foudn! " + source);
        String author = intent.getStringExtra("author");
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String url = intent.getStringExtra("url");
        String urltoimage = intent.getStringExtra("urltoimage");
        String publishedate = intent.getStringExtra("publishedat");
        String content = intent.getStringExtra("content");

        TextView tvSource = findViewById(R.id.source);
        TextView tvAuthor = findViewById(R.id.author);
        TextView tvTitle = findViewById(R.id.title);
        TextView tvDescription = findViewById(R.id.description);
        TextView tvUrl = findViewById(R.id.url);
        ImageView imageThumbnail = findViewById(R.id.smallThumbnail);
        TextView tvPublishedAt = findViewById(R.id.publishedAt);
        TextView tvContent = findViewById(R.id.content);

        tvAuthor.setText("by " + author);
        tvTitle.setText(title);
        tvDescription.setText(description);
        tvUrl.setText(url);
        tvPublishedAt.setText(publishedate);
        tvContent.setText(content);


    }
}