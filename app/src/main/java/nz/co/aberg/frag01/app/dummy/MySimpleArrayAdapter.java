package nz.co.aberg.frag01.app.dummy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nz.co.aberg.frag01.app.R;

/**
 * Created by aberg on 13/05/14.
 */
public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;

    public MySimpleArrayAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.activity_main, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_main, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.withText);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


//                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.aven);
//                if (bm != null && imageView != null) {
//                    imageView.setImageBitmap(bm);
//                }

        textView.setText(values.get(position));

        // change the icon for Windows and iPhone
//                String s = values[position];
//                if (s.startsWith("iPhone")) {
//                    imageView.setImageResource(R.drawable.no);
//                } else {
//                    imageView.setImageResource(R.drawable.ok);
//                }

        return rowView;
    }
}