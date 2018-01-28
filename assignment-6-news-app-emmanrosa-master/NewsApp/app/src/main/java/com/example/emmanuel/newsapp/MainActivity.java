package com.example.emmanuel.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<news_list>>, SwipeRefreshLayout.OnRefreshListener {
    private news_adapater adapter;
    private news_list n_list;
    private static int LOADER_ID = 0;
    SwipeRefreshLayout swipe;
    //String username = "2ca74584-70d0-488d-a679-3d8d0f0b6352";
    //String password = "H2UX11eDwY7x";
    //final String VERSION_DATE = "2017-07-01";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipe.setOnRefreshListener(this);
        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new news_adapater(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                news_list news = adapter.getItem(i);
                String url = news.urls;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

    }

    @Override
    public Loader<List<news_list>> onCreateLoader(int id, Bundle args) {
        return new napp_loader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<news_list>> loader, List<news_list> data) {
        swipe.setRefreshing(false);
        if (data != null) {
            adapter.setNotifyOnChange(false);
            adapter.clear();
            adapter.setNotifyOnChange(true);
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<news_list>> loader) {

    }

    @Override
    public void onRefresh() {

        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }

/*
    public void translateToGerman(View view) {
        String requestURL = "https://translation.googleapis.com/language/translate/v2";
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<String, String>("key", getResources().getString(R.string.mykey)));
        params.add(new Pair<String, String>("source", "en"));
        params.add(new Pair<String, String>("target", "de"));

        String[] queries = ((TextView)findViewById(R.id.resultsText)).getText().toString().split("\n");
        for(String query:queries) {
            params.add(new Pair<String, String>("q", query));
        }

        Fuel.get(requestURL, params).responseString(new Handler<String>() {
            @Override
            public void success(@NotNull Request request, @NotNull Response response, String data) {
                try {
                    JSONArray translations = new JSONObject(data)
                            .getJSONObject("data")
                            .getJSONArray("translations");

                    String result = "";
                    for(int i=0;i<translations.length();i++) {
                        result += translations.getJSONObject(i).getString("translatedText") + "\n";
                    }

                    ((TextView)findViewById(R.id.resultsText)).setText(result);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError fuelError) {

            }
        });
    }
    */

}

