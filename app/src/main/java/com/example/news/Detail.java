package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Detail extends AppCompatActivity {

    Model model1 = new Model();

    TextView detailheading,details, newscontent;
    ImageView imageView;
    NewsApiClient newsApiClient = new NewsApiClient("840a317ab9944f94bb82811f863c1599");
    ArrayList<Model> modelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailheading = findViewById(R.id.detail_tv);
        details = findViewById(R.id.detail_details);
        newscontent = findViewById(R.id.detail_newscontent);
        imageView = findViewById(R.id.detail_img);

        init();
    }

    void init() {
        Gson gson = new Gson();
        String jsonOutput = getIntent().getStringExtra("modelArrayList");
        Type listType = new TypeToken<Model>() {
        }.getType();
        model1 = gson.fromJson(jsonOutput, listType);

        Picasso.with(getApplicationContext()).load(model1.getUrlimg()).into(imageView);
        //title.setText(model1.getDescription());
        detailheading.setText(model1.getTitle());
        details.setText(parseDateToddMMyyyy(model1.getPublish()));
        newscontent.setText(model1.getDescription());


    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        String outputPattern = "dd-MM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.ENGLISH);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


}