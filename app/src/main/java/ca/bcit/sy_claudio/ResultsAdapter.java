package ca.bcit.sy_claudio;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsAdapter extends ArrayAdapter<Results> {
    Context _context;
    public ResultsAdapter(Context context, ArrayList<Results> results) {
        super(context, 0, results);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        Results result = getItem(position);
        // Check if an existing view is being reused, otherwies inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }

        // Lookup view for data population
        TextView tvId = convertView.findViewById(R.id.id);
        TextView tvAuthor = convertView.findViewById(R.id.author);
        TextView tvTitle = convertView.findViewById(R.id.title);

        // Populate the data into the template view using the data object
        tvId.setText(result.getId());
        tvAuthor.setText(result.getAuthor());
        tvTitle.setText(result.getTitle());

        // Return the completed view to render on screen
        return convertView;
    }
}
