package cphbusiness.kasper.pagh.httpexample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendGet(View view)
    {
        if (networkChecker())
        {
            new Getter(this).execute("https://jsonplaceholder.typicode.com/posts/1");
        } else
        {
            Toast.makeText(getApplicationContext(), "WE REQUIRE ADDITIONAL INTERNET! (ie. tænd for nettet din abe!)", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendPost(View view)
    {
        if (networkChecker())
        {
            new Poster(this, new DummyJSON("Dette er svaret fra et POST request", "Parset med Gson")).execute("http://jsonplaceholder.typicode.com/posts");
        } else
        {
            Toast.makeText(getApplicationContext(), "WE REQUIRE ADDITIONAL INTERNET! (ie. tænd for nettet din abe!)", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendPut(View view)
    {
        if (networkChecker())
        {
            new Putter(this, new DummyJSON("Dette er svaret fra et PUT request", "Parset med Gson")).execute("http://jsonplaceholder.typicode.com/posts/1");
        } else
        {
            Toast.makeText(getApplicationContext(), "WE REQUIRE ADDITIONAL INTERNET! (ie. tænd for nettet din abe!)", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendDelete(View view)
    {
        if (networkChecker())
        {

        } else
        {
            Toast.makeText(getApplicationContext(), "WE REQUIRE ADDITIONAL INTERNET! (ie. tænd for nettet din abe!)", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean networkChecker()
    {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        //Denne del er meget vigtig, da netwærk ikke nødvendigvis er et given på mobile devices! Derfor skal man have noget logik der giver en fejl i tilfælde af intet net!
        if (networkInfo != null && networkInfo.isConnected())
        {
            return true;
        } else
        {
            return false;
        }
    }
}
