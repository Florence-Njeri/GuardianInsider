package com.example.admin.guardianinsyder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
   private ArrayList<News>   newsArrayList;
Context context;
    public NewsAdapter(Context context,ArrayList<News> newsArrayList){
        this.context=context;
        this.newsArrayList=newsArrayList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

//Inflate the view if its empty
        View view = LayoutInflater.from(context).inflate(R.layout.news_recycler_view, viewGroup, false);
        return new NewsViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {
        final News news=newsArrayList.get(position);
//Create a new view and bind it with the data obtained from the News class
      newsViewHolder.title.setText(news.getTitle());
      newsViewHolder.author.setText(news.getAuthor());
      newsViewHolder.genre.setText(news.getGenre());
      newsViewHolder.date.setText(news.getDate());
      newsViewHolder.url.setText(news.getUrl());
    }

    @Override
    public int getItemCount() {
        /**Return the size of the obtained news ArrayList
         * @param newsArrayList == null ? 0  added to fix the null pointer exception causing app to crush
         */

        return newsArrayList == null ? 0 : newsArrayList.size();
    }

    public  class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView author;
        TextView genre;
        TextView url;
        TextView date;
        public NewsViewHolder(@NonNull View itemView) {


            super(itemView);

            title=itemView.findViewById(R.id.headline);
            author=itemView.findViewById(R.id.author);
            genre=itemView.findViewById(R.id.genre);
            date=itemView.findViewById(R.id.publication_date);

        }
    }
}
