package com.napps.flixer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.napps.flixer.R;
import com.napps.flixer.models.Movie;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movies = movies;
    }

    // Inflating Layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Populating Data
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get movie position
        Movie movie = movies.get(position);
        // Bind the movie data in the viewholder
        holder.bind(movie);
    }

    // Return Total number of items
    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView MovieTitle;
        TextView MovieOverview;
        ImageView MovieAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MovieTitle = itemView.findViewById(R.id.MovieTitle);
            MovieOverview = itemView.findViewById(R.id.MovieOverview);
            MovieAvatar = itemView.findViewById(R.id.MovieAvatar);
        }

        public void bind(Movie movie) {
            MovieTitle.setText(movie.getTitle());
            MovieOverview.setText(movie.getOverview());
            Glide.with(context).load(movie.getPosterPath()).into(MovieAvatar);
        }
    }
}
