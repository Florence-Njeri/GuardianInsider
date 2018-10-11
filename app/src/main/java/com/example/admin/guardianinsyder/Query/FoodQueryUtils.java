package com.example.admin.guardianinsyder.Query;

import android.util.Log;

import com.example.admin.guardianinsyder.News;

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
import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class FoodQueryUtils {

    private FoodQueryUtils(){

    }
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * Used to connect to @param FOOD_NEWS_URL, check if connected and when its connected get JSON
     * data returned by @method readFromStream() which returns the whole file containing JSON
     *
     */

    private static String checkHttpConnection(URL url) throws IOException {
//Fist convert the url String to URL object

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }
        /*Instantiate the HttpURLConnection and InputStreamBuilder
         * @class HttpURLConnection gets the JSON data and on connection successful,
         * returns server response via InputStream
         */

        HttpURLConnection httpURLConnection;
        InputStream inputStream;

        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setReadTimeout(10000 /* milliseconds */);
        httpURLConnection.setConnectTimeout(15000 /* milliseconds */);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();

        //Check whether the connection is successfull when true read from the InputStream and parse the response
        if (httpURLConnection.getResponseCode() == 200) {
            inputStream = httpURLConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } else {
            Log.e(TAG, "Connection not successful");
        }
        return jsonResponse;
    }

    /**
     * once the connection is successful,  Fetch data from the website into your app
     */

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder builderOutput = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                builderOutput.append(line);
                line = reader.readLine();
            }
        } else {
            Log.e(TAG, "Empty Input Stream Reader");
        }
        return builderOutput.toString();
    }

    /*After obtaining JSON from the URL, you can now read the JSON data contained in it
     * Since it will return a list of extracted news the dataType will be ArrayList and so will the return data
     */

    private static ArrayList <News> getJSONData(String JSONData)  {
        //New News ArrayList to be populated with the data obtained
        ArrayList <News> foodNewsArrayList = new ArrayList <>();
        //First convert the JSON String from the checkHttpConnection() into a JSON Object
        try{
            JSONObject rootJsonObject = new JSONObject(JSONData);
            JSONObject newsObject=rootJsonObject.getJSONObject("response");
            JSONArray jsonArray = newsObject.getJSONArray("results");

            //Use a loop to pass through the entire array
            for (int i = 0; i <= jsonArray.length(); i++) {

                JSONObject currentObject = jsonArray.getJSONObject(i);
                //Extract what you need
                String title = currentObject.getString("webTitle");
                String genre = currentObject.getString("sectionName");
                String dateOfPublication = currentObject.getString("webPublicationDate");
                String url = currentObject.getString("webUrl");

                //Then add the object to the arrayList
                foodNewsArrayList.add( new News(title, genre, dateOfPublication, url));
            }
        }
        catch(JSONException e){
            Log.e("QueryUtils", "Problem parsing news JSON results", e);
        }


        return foodNewsArrayList;
    }

    public static ArrayList<News> fetchJsonData(String requestedUrl){

        //Create a URL object
        URL url=createUrl(requestedUrl);
        //Perform HTTPRequest and receive the response back
        String jsonResponse = null;
        try{
            jsonResponse=checkHttpConnection(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
//Now extract from the JSON response
        ArrayList<News> foodNewsArrayList;

        foodNewsArrayList = getJSONData(jsonResponse);

//Should return the list of news obtained to update the UI
        return foodNewsArrayList;
    }
}
