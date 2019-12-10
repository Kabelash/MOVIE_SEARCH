package com.kabelash.moviessearch.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kabelash.moviessearch.R;
import com.kabelash.moviessearch.model.MovieDetails;
import com.kabelash.moviessearch.model.Search;
import com.kabelash.moviessearch.utils.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.kabelash.moviessearch.utils.Constants.MOVIE_URL;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView actors;
    private ImageView poster;
    private TextView producer;
    private TextView director;
    private TextView year;
    private TextView genre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        //Getting id from string extra
        String imdbID = getIntent().getStringExtra("IMDB_ID");

        title = findViewById(R.id.mTitle);
        actors = findViewById(R.id.mActors);
        producer = findViewById(R.id.mProducer);
        director = findViewById(R.id.mDirector);
        year = findViewById(R.id.mYear);
        genre = findViewById(R.id.mGenre);

        poster = findViewById(R.id.mPoster);

        //Fetch movie details
        fetchMovieDetails(MOVIE_URL + imdbID);
    }

    private void fetchMovieDetails(String url) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                                String stitle = response.getString("Title");
                                String sactors = response.getString("Actors");
                                String sproducer = response.getString("Production");
                                String sdirector = response.getString("Director");
                                String syear = response.getString("Year");
                                String sgenre = response.getString("Genre");
                                String sposter = response.getString("Poster");


                                title.setText(stitle);
                                actors.setText(sactors);
                                producer.setText(sproducer);
                                director.setText(sdirector);
                                year.setText(syear);
                                genre.setText(sgenre);

                            Glide.with(MovieDetailsActivity.this)
                                    .load(sposter)
                                    //.apply(RequestOptions.circleCropTransform())
                                    .into(poster);

                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json for testing
                //Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
    }
}
