package com.kabelash.moviessearch.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kabelash.moviessearch.R;
import com.kabelash.moviessearch.model.Search;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Search> movieList;
    private List<Search> movieListFiltered;
    private MovieAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.movieTitle);
            thumbnail = view.findViewById(R.id.moviePoster);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected movie in callback
                    listener.onMovieSelected(movieListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public MovieAdapter(Context context, List<Search> movieList, MovieAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.movieList = movieList;
        this.movieListFiltered = movieList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Search movieSearch = movieListFiltered.get(position);
        holder.name.setText(movieSearch.getTitle());

        Glide.with(context)
                .load(movieSearch.getPoster())
                //.apply(RequestOptions.circleCropTransform())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return movieListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    movieListFiltered = movieList;
                } else {
                    List<Search> filteredList = new ArrayList<>();
                    for (Search row : movieList) {

                        // Matching with movie title
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    movieListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = movieListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieListFiltered = (ArrayList<Search>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface MovieAdapterListener {
        void onMovieSelected(Search movieSearch);
    }

}
