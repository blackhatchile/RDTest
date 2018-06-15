package com.example.guest.rdesingtest;

import android.content.ClipData;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.guest.rdesingtest.Adapter.LoadItem;
import com.example.guest.rdesingtest.Adapter.ReciclerAdapter;
import com.example.guest.rdesingtest.SwipeHelper.SwipeDeleteHelper;
import com.example.guest.rdesingtest.SwipeHelper.SwipeDeleteHelperListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Ignacio Arriagada Ascencio - ReingDesing Android Development Testing.
 * 12-06-2018
 * */

public class   MainActivity extends AppCompatActivity implements SwipeDeleteHelperListener {

    //Android HN API JSON Query...
    //Seeing JSON File with https://codebeautify.org/jsonviewer...
    private static final String URL_DATA_API = "https://hn.algolia.com/api/v1/search_by_date?query=android";

    private RecyclerView recyclerView;
    private ReciclerAdapter adapter;
    public SwipeRefreshLayout swipeRefresh;

    private List<LoadItem> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Ignacio Reing Desing Test");

        recyclerView = findViewById(R.id.hnrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefresh = findViewById(R.id.swipe_layer);

        list = new ArrayList<>();

        //Loading data from API...
        loadViewData();

        //Calling Swipe Helper...
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SwipeDeleteHelper(0,ItemTouchHelper.LEFT, this);

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        //MOCKUP RECYCLER FUNCTION...
        //adapter = new ReciclerAdapter(list, this);
        //recyclerView.setAdapter(adapter);
    }

    //Fetching data server from JSON HN API with Volley JCenter...
    private void loadViewData() {

        StringRequest string = new StringRequest(Request.Method.GET, URL_DATA_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray array = json.getJSONArray("hits");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                LoadItem item = new LoadItem(
                                        o.getString("story_title"),
                                        o.getString("author"),
                                        o.getString("created_at"),
                                        o.getString("story_url")
                                );
                                list.add(item);
                            }

                            adapter = new ReciclerAdapter(list, getApplicationContext(),swipeRefresh);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {

                            e.printStackTrace();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast or Alert dialog response...
                    }
                });

                RequestQueue request = Volley.newRequestQueue(this);
                request.add(string);

        }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if(viewHolder instanceof ReciclerAdapter.ViewHolder){
            String name = list.get(viewHolder.getAdapterPosition()).getAuthor();

            LoadItem delete = list.get(viewHolder.getAdapterPosition());
            int index = viewHolder.getAdapterPosition();

            adapter.removeItem(index);
        }
    }
}
