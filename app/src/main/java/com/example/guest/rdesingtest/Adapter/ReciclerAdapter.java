package com.example.guest.rdesingtest.Adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.rdesingtest.R;
import com.example.guest.rdesingtest.WebViewer.WebViewActivity;

import java.util.List;
import java.util.Random;

public class ReciclerAdapter extends RecyclerView.Adapter<ReciclerAdapter.ViewHolder>{

    private List<LoadItem> loadItems;
    private Context context;

    public SwipeRefreshLayout swipeRefresh;

    public ReciclerAdapter(List<LoadItem> loadItems, Context context, SwipeRefreshLayout swipeRefresh) {
        this.loadItems = loadItems;
        this.context = context;
        this.swipeRefresh = swipeRefresh;
    }

    @Override
    public ReciclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            final LoadItem item = loadItems.get(position);

            holder.textvtitle.setText(item.title_story);
            holder.textvauthor.setText(item.author);
            holder.textvcreated.setText(item.created_at);

            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //String url_test = "https://www.nytimes.com/2018/06/12/upshot/if-the-robots-come-for-our-jobs-what-should-the-government-do.html";
                    String url_test = item.url_access;
                    Intent intent = new Intent(view.getContext(), WebViewActivity.class);
                    intent.putExtra("access_web", url_test);
                    view.getContext().startActivity(intent);
                }
            });

          swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
              @Override
              public void onRefresh() {

                  getData();
              }
          });
    }

    @Override
    public int getItemCount() {

        return loadItems.size();
    }

    //Getting swipe update data...
    private void getData(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            loadItems.add(0, loadItems.get(new Random().nextInt(loadItems.size())));

                ReciclerAdapter.this.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);

            }

        }, 3000);
    }

    public void removeItem(int position){

        loadItems.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //public CardView card;
        //public LinearLayout linearLayout;
        public FrameLayout frameLayout;
        public RelativeLayout view_background, view_front;

        public TextView textvtitle;
        public TextView textvauthor;
        public TextView textvcreated;

        public ViewHolder(View itemView) {
            super(itemView);
            //card = itemView.findViewById(R.id.hncard);
            frameLayout = itemView.findViewById(R.id.frameClick);
            view_background = itemView.findViewById(R.id.view_background);
            view_front = itemView.findViewById(R.id.view_front);

            textvtitle = itemView.findViewById(R.id.titletextview);
            textvauthor = itemView.findViewById(R.id.authortextview);
            textvcreated = itemView.findViewById(R.id.createdtextview);
        }
    }
}

