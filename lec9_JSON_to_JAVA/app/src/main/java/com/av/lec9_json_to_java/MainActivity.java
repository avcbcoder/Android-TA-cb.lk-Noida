package com.av.lec9_json_to_java;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


/*https://newsapi.org/
  https://jsonformatter.curiousconcept.com/
 */
public class MainActivity extends AppCompatActivity {

    public static final String STATUS = "status";
    public static final String TOTAL_RESULTS = "totalResults";
    public static final String ARTICLES_json = "articles";
    public static final String AUTHOR = "author";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String URL_json = "url";
    public static final String URL_TO_IMAGE = "urlToImage";
    public static final String PUBLISHED_AT = "publishedAt";
    public static final String SOURCE = "source";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TAG = "MainActivity";

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tvResult);
        AsyncJSONTask task = new AsyncJSONTask();
        task.execute("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=6c98b7e685cc4b7e93076ac09c9d4639");
    }

    class AsyncJSONTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream stream = httpURLConnection.getInputStream();
                Scanner sc = new Scanner(stream);
                sc.useDelimiter("\\A");
                if (sc.hasNext()) {
                    String s = sc.next();
                    Log.e(TAG, "*************** \n" + s);
                    return s;
                }
            } catch (Exception e) {

            }
            return "";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
            Result r = convertJSONtoJAVA(s);
            ArrayList<Article> al = r.getArticles();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private Result convertJSONtoJAVA(String s) {
        try {
            // parse string to JSON root object
            JSONObject root = new JSONObject(s);
            String status = root.getString(STATUS); // First param of root
            Integer totalResults = root.getInt(TOTAL_RESULTS); // Second param
            JSONArray JSON_Array = root.getJSONArray(ARTICLES_json); // Third param is arrayList

            ArrayList<Article> articles = new ArrayList<>();
            for (int i = 0; i < JSON_Array.length(); i++) {
                // getting each article json object one by one
                JSONObject articleobj = JSON_Array.getJSONObject(i);
                // Getting source object inside of article object
                JSONObject sourceObj = articleobj.getJSONObject(SOURCE);

                String id = sourceObj.getString("id");
                String name = sourceObj.getString("name");
                Source source = new Source(id, name);

                articles.add(new Article(
                        articleobj.getString(AUTHOR),
                        articleobj.getString(TITLE),
                        articleobj.getString(DESCRIPTION),
                        articleobj.getString(URL_json),
                        articleobj.getString(URL_TO_IMAGE),
                        articleobj.getString(PUBLISHED_AT),
                        source
                ));
            }
            StringBuilder sb = new StringBuilder("");
            for (Article a : articles) {
                sb.append(a.toString());
                sb.append("\n\n\n");
            }
            tv.setText(sb.toString());
            return new Result(status, totalResults, articles);
        } catch (Exception e) {

        }
        return null;
    }

}


/*
JSON STRUCTURE

{
   "status":"ok",
   "totalResults":20,
   "articles":[
      {   // Source Object
         "source":{
            "id":"bloomberg",
            "name":"Bloomberg"
         },
         "author":"Jessica Summers",
         "title":"Oil Set for Weekly Surge as Saudis Fail to Allay Supply Fears",
         "description":"Crude headed for its biggest weekly surge in more than two months as shrinking stockpiles and supply disruptions from Canada to Libya compounded the growing isolation of OPEC’s third-largest oil producer.",
         "url":"https://www.bloomberg.com/news/articles/2018-06-28/oil-set-for-biggest-weekly-gain-in-11-weeks-on-disruption-risks",
         "urlToImage":"https://assets.bwbx.io/images/users/iqjWHBFdfxIU/iXZ8kPAEGOIQ/v0/1200x799.jpg",
         "publishedAt":"2018-06-29T15:42:18Z"
      },
      {+}, // Another Article
      {+}  // Another
 */