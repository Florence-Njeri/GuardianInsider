package com.example.admin.guardianinsyder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        newsViewHolder.genre.setText(news.getAuthor());
        newsViewHolder.date.setText(news.getDate());
        newsViewHolder.author.setText(news.getAuthor());

        newsViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String webSiteUrl=news.getUrl();

                Intent websiteIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(webSiteUrl));
                context.startActivity(websiteIntent);
            }
        });

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
        TextView date;
        TextView genre;
        CardView cardView;
        public NewsViewHolder(@NonNull View itemView) {


            super(itemView);

            title=itemView.findViewById(R.id.headline);
            author=itemView.findViewById(R.id.author);
            date=itemView.findViewById(R.id.publication_date);
            genre=itemView.findViewById(R.id.genre);
            cardView=itemView.findViewById(R.id.card_view);

        }
    }

}
