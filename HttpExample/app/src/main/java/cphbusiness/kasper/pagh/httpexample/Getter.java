package cphbusiness.kasper.pagh.httpexample;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kaspe on 2016-10-22.
 */

public class Getter extends AsyncTask<String, Void, String>
{

    View view;
    AppCompatActivity app;
    Context context;

    public Getter(AppCompatActivity app)
    {
//        this.view = view;
        this.app = app;
//        this.context = context;
    }
    @Override
    protected String doInBackground(String... urls)
    {
        // params comes from the execute() call: params[0] is the url.
        try
        {
            return downloadUrl(urls[0]);
        } catch (IOException e)
        {
            Toast.makeText(app.getApplicationContext() , "Unable to complete request (IOException)", Toast.LENGTH_SHORT).show();
            return "Unable to retrieve web page. URL may be invalid.";
        }
    }

    @Override
    protected void onPostExecute(String result)
    {
        TextView textView = (TextView) app.findViewById(R.id.responseText);
        textView.setText(result);
    }

    private String downloadUrl(String myurl) throws IOException
    {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try
        {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally
        {
            if (is != null)
            {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException
    {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}
