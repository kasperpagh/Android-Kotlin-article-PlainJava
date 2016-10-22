package cphbusiness.kasper.pagh.httpexample;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kaspe on 2016-10-22.
 */

public class Putter extends AsyncTask<String, Void, String>
{
    Gson gson;
    View view;
    AppCompatActivity app;
    Context context;
    DummyJSON jsonString;


    public Putter(AppCompatActivity app, DummyJSON json)
    {
//        this.view = view;
        this.app = app;
        this.jsonString = json;
        gson = new Gson();

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
        OutputStream out = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;
        String jsonString1 = gson.toJson(jsonString);
        try
        {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("PUT");

            //set the sending type and receiving type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);



            out = conn.getOutputStream();
            out.write(jsonString1.getBytes("UTF-8"));
            out.close();


            // Starts the query
            conn.connect();

            is = conn.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            bufferedReader.close();
            Log.d(getClass().getSimpleName(),"her er return fra DL: ");
            return sb.toString();
        } finally
        {

            if (is != null)
            {
                is.close();
            }

        }
    }

}
