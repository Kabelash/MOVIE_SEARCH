package com.kabelash.moviessearch.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kabelash.moviessearch.R;
import com.kabelash.moviessearch.model.Search;
import com.kabelash.moviessearch.ui.adapter.MovieAdapter;
import com.kabelash.moviessearch.utils.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.kabelash.moviessearch.utils.Constants.API_URL;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Search> movieList;
    private MovieAdapter mAdapter;
    private SearchView searchView;
    private String searchQuery;
    ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pBar= findViewById(R.id.progressBar);
        pBar.setVisibility(View.GONE);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        movieList = new ArrayList<>();
        mAdapter = new MovieAdapter(this, movieList, this);

        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager( 2, GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //Fetch Json
        fetchMovies(API_URL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);

                //Setting progress bar visible
                pBar.setVisibility(View.VISIBLE);

                //To fetch movies with search query
                fetchMovies(API_URL + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //To call searchview
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        // close search on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onMovieSelected(Search movieSearch) {
        Toast.makeText(getApplicationContext(), movieSearch.getTitle(), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("IMDB_ID", movieSearch.getimdbID());
        startActivity(intent);
    }


    private void fetchMovies(String url) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Search");

                            List<Search> items = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<Search>>() {
                            }.getType());

                            // adding movies to list
                            movieList.clear();
                            movieList.addAll(items);

                            //Setting progress bar invisible
                            pBar.setVisibility(View.GONE);
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                        // refreshing recycler view
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json for testing
                Log.e(TAG, "Error: " + error.getMessage());
                //Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
    }
}
