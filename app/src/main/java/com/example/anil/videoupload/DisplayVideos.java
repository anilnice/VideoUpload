package com.example.anil.videoupload;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DisplayVideos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<SingleData> videodata;
    private ProgressDialog progressDialog;
    private String urlWebService="https://anil12345678.000webhostapp.com/fetchvideos.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_videos);
        videodata=new ArrayList<SingleData>();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        class Videodatafetch extends AsyncTask<String,Void,String>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(DisplayVideos.this,"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                try {
                    JSONArray object=new JSONArray(s);
                    for (int i=0;i<=object.length();i++){
                        JSONObject a=object.getJSONObject(i);
                        SingleData currentdata=new SingleData();
                        int id=a.getInt("id");
                        String name=a.getString("video_name");
                        String path=a.getString("video_path");
                        currentdata.setId(id);
                        currentdata.setVideo_name(name);
                        currentdata.setVideo_path(path);
                        videodata.add(currentdata);
                        recyclerView.setAdapter(new Myadapter(DisplayVideos.this,videodata));
                        Log.d("hi",  ""+id+"   "+name+"   "+path);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(DisplayVideos.this, ""+s, Toast.LENGTH_SHORT).show();
            }
        }
        Videodatafetch videodatafetch=new Videodatafetch();
        videodatafetch.execute();

    }
}
