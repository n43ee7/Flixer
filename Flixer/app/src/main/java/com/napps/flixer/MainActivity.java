package com.napps.flixer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.napps.flixer.adapters.MovieAdapter;
import com.napps.flixer.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    public static final String PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String Activity_TAG = "MainRoutine";
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView MoviesView = findViewById(R.id.MovieView);
        movies = new ArrayList<>();

        // Create the adapter
        final MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        // Set the adapter on the Recycler View
        MoviesView.setAdapter(movieAdapter);
        // Set a layout manager on the recycler view
        MoviesView.setLayoutManager(new LinearLayoutManager(this));

        // HTTP Client setup
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                // 200 Ok
                Log.d(Activity_TAG, "[!] Handler OK!");     // Debugger Uses
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray query = jsonObject.getJSONArray("results");
                    Log.i(Activity_TAG, "[!] Json Query Accepted::" + query.toString());    // Debugger entry

                    movies.addAll(Movie.fromJsonArray(query));
                    movieAdapter.notifyDataSetChanged();

                    Log.i(Activity_TAG, "[+] Movie::" + movies.toString());                 // Debugger entry

                }
                catch (JSONException e) {
                    Log.e(Activity_TAG, "[!] Hit Json Exception!", e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(Activity_TAG, "[!] Handler error");   // Debugger Uses
            }
        });
    }
}