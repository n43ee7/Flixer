package com.napps.flixer.models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.napps.flixer.R;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    TextView MovieTitle;
    TextView MovieOverview;
    RatingBar MovieRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Setting Dynmaic UI Color
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        @SuppressLint("CutPasteId") View someView = findViewById(R.id.MovieView);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.UIblack));
        @SuppressLint("CutPasteId") RecyclerView MoviesView = findViewById(R.id.MovieView);

        MovieTitle = findViewById(R.id.DetailedMovieTitle);
        MovieOverview = findViewById(R.id.DetailedMovieOver);
        MovieRating = findViewById(R.id.MovieRating);

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        assert movie != null; // Assertion
        MovieTitle.setText(movie.getTitle());
        MovieOverview.setText(movie.getOverview());
        MovieRating.setRating((float) movie.getRating());
        // parse out extra additional data post aaa
    }
}