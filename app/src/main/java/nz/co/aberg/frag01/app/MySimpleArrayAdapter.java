package nz.co.aberg.frag01.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by aberg on 13/05/14.
 */
public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;

    public MySimpleArrayAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.shots_main, values);
        this.context = context;
        this.values = values;
    }

    public class DownloadImagesTask extends AsyncTask<ImageView, Void, Bitmap> {

        ImageView imageView = null;

        @Override
        protected Bitmap doInBackground(ImageView... imageViews) {
            this.imageView = imageViews[0];
            return download_Image((String) imageView.getTag());
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }


        private Bitmap download_Image(String url) {

            Bitmap bmp =null;
            try{
                URL ulrn = new URL(url);
                HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
                InputStream is = con.getInputStream();
                bmp = BitmapFactory.decodeStream(is);
                if (null != bmp){
//                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bmp, 128,
//                            96, false);
//                    return scaledBitmap;
                    return bmp;
                }

            }catch(Exception e){}
            return bmp;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.shots_main, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textView6);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);

        String URL = "https://www.google.co.nz/images/srpr/logo11w.png";
//        String URL = "http://www.cinemablend.com/images/news/36006/_1361891471.jpg";
//        String URL = "http://motionographer.com/wp-content/uploads/2009/07/vfx-for-directors.jpg";

        imageView.setTag(URL);
        new DownloadImagesTask().execute(imageView);
        textView.setText(values.get(position));



        return rowView;
    }




}