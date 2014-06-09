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
    public ImageLoader imageLoader;

    public MySimpleArrayAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.shots_main, values);
        this.context = context;
        this.values = values;
        imageLoader=new ImageLoader(context);
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

    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = convertView;
        ViewHolder viewHolder;
//        rowView = inflater.inflate(R.layout.shots_main, parent, false);

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.shots_main, parent, false);

            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.textView6);
            viewHolder.image = (ImageView) rowView.findViewById(R.id.imageView);
            rowView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) rowView.getTag();
        }

//        String URL = "https://www.google.co.nz/images/srpr/logo11w.png";
//        String URL = "http://img.photobucket.com/albums/v58/PixelMagic/Blog%20Images/vfx_tip_2_lightwrap.jpg";
//        String URL = "http://www.artofvfx.com/TRONLEGACY/TRONLEGACY_OLLIN_VFX_03B.jpg";
        String URL = "http://artofvfx.com/wp-content/uploads/2011/03/BLA_SPIN_VFX_03A.jpg";
        viewHolder.image.setTag(URL);
//        new DownloadImagesTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, viewHolder.image);
        imageLoader.DisplayImage(URL, viewHolder.image);
        viewHolder.text.setText(values.get(position));

//        rowView = inflater.inflate(R.layout.shots_main, parent, false);

//        TextView textView = (TextView) rowView.findViewById(R.id.textView6);
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);


//        String URL = "http://www.cinemablend.com/images/news/36006/_1361891471.jpg";
//        String URL = "http://motionographer.com/wp-content/uploads/2009/07/vfx-for-directors.jpg";




        return rowView;
    }




}