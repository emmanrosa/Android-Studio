package com.example.emmanuel.newsapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by emmanuel on 10/24/17.
 */

public class QueryUtils {
    public MainActivity mMainActivity;
    private final Context mContext;
    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public QueryUtils(Context context, MainActivity mainActivity) {
        mContext = context;
        mMainActivity = mainActivity;
    }

        static String createStringUrl() {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
            .encodedAuthority("content.guardianapis.com")
            .appendPath("search")
            .appendQueryParameter("order-by", "newest")
            .appendQueryParameter("show-references", "author")
            .appendQueryParameter("show-tags", "contributor")
            .appendQueryParameter("api-key", "test");
            String url = builder.build().toString();
    return url;
}

        static URL createUrl() {
            String stringUrl = createStringUrl();
            try {
                return new URL(stringUrl);
            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Problem building the URL ", e);
                return null;
            }
        }


/**
 * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
 */
/*
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    */

    //
        //https://stackoverflow.com/questions/454315/how-do-you-format-date-and-time-in-android
        private static String formatDate(String rawDate) {
            String jsonDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
            SimpleDateFormat jsonFormatter = new SimpleDateFormat(jsonDatePattern, Locale.US);
            try {
                Date parsedJsonDate = jsonFormatter.parse(rawDate);
                String finalDatePattern = "MMM d, yyy";
                SimpleDateFormat finalDateFormatter = new SimpleDateFormat(finalDatePattern, Locale.US);
                return finalDateFormatter.format(parsedJsonDate);
            } catch (ParseException e) {
                Log.e(LOG_TAG, "Problem parsing JSON date: ", e);
                return "";
            }
        }

        static String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";

            if (url == null){
                return jsonResponse;
            }
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;

            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();

                // If the request was successful (response code 200),
                // then read the input stream and parse the response.
                if (urlConnection.getResponseCode() == 200){
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        private static String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        static List<news_list> parseJson(String response) {
            ArrayList<news_list> listOfNews = new ArrayList<>();
            try {
                JSONObject jsonResponse = new JSONObject(response);
                JSONObject jsonResults = jsonResponse.getJSONObject("response");
                JSONArray resultsArray = jsonResults.getJSONArray("results");

                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject oneResult = resultsArray.getJSONObject(i);
                    String webTitle = oneResult.getString("webTitle");
                    String url = oneResult.getString("webUrl");
                    String date = oneResult.getString("webPublicationDate");
                    date = formatDate(date);
                    String section = oneResult.getString("sectionName");
                    JSONArray tagsArray = oneResult.getJSONArray("tags");
                    String author = "";

                    if (tagsArray.length() == 0) {
                        author = null;
                    } else {
                        for (int j = 0; j < tagsArray.length(); j++) {
                            JSONObject firstObject = tagsArray.getJSONObject(j);
                            author += firstObject.getString("webTitle") + ". ";
                        }
                    }
                    listOfNews.add(new news_list(webTitle, author, url, date, section));
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
            }
            return listOfNews;
        }
}