package ca.bcit.sy_claudio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

public class ResultsAdapter extends ArrayAdapter<Results> {
    Context _context;
    public ResultsAdapter(Context context, ArrayList<Results> results) {
        super(context, android.R.layout.simple_list_item_1, results);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        final Results result = getItem(position);
        // Check if an existing view is being reused, otherwies inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }

        // Lookup view for data population
        TextView tvTitle = convertView.findViewById(R.id.title);

        // Populate the data into the template view using the data object
        tvTitle.setText(result.getTitle());

        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ResultDataActivity.class);

                // pass in string values of json to the intent
                intent.putExtra("source", result.getSource().getName());
                intent.putExtra("author", result.getAuthor());
                intent.putExtra("title", result.getTitle());
                intent.putExtra("description", result.getDescription());
                intent.putExtra("url", result.getUrl());
                intent.putExtra("urltoimage", result.getUrlToImage());
                intent.putExtra("publishedat", result.getPublishedAt());
                intent.putExtra("content", result.getContent());

                view.getContext().startActivity(intent);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
