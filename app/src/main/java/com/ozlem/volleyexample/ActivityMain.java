package com.ozlem.volleyexample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONObject;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final Button am_button1 = (Button) findViewById(R.id.am_button1);
        final TextView am_tv1 = (TextView) findViewById(R.id.am_tv1);
        am_button1.setText("Press to see the json response from http://echo.jsontest.com/key/value/fruit/apple");
        am_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                am_button1.setText("Loading...");
                am_tv1.setText("");

                String url = "http://echo.jsontest.com/key/value/fruit/apple";

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                am_button1.setText("Done");
                                am_tv1.setText("Response: " + " " + response.toString());
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        am_button1.setText("Error");
                    }
                });

                SingletonVolley.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

            }
        });

        final Button am_button2 = (Button) findViewById(R.id.am_button2);
        final ImageView am_im1 = (ImageView) findViewById(R.id.am_im1);
        am_button2.setText("http://developer.android.com/images/training/system-ui.png");
        am_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                am_button2.setText("Loading...");


                final String url = "http://developer.android.com/images/training/system-ui.png";

                //clearCache(url);

                final NetworkImageView mNetworkImageView = (NetworkImageView) findViewById(R.id.am_im1);

                final ImageLoader mImageLoader = SingletonVolley.getInstance(getApplicationContext()).getImageLoader();


                mImageLoader.get(url, new ImageLoader.ImageListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        am_button2.setText("Error");


                    }

                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        am_button2.setText("Done");
                        mNetworkImageView.setImageUrl(url, mImageLoader);
                    }
                });


            }
        });

    }

    private void clearCache(String url) {

        SingletonVolley.getInstance(this).getRequestQueue().getCache().clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
