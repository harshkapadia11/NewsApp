package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {
    RecyclerView recyclerView;
    NewsApiClient newsApiClient = new NewsApiClient("840a317ab9944f94bb82811f863c1599");
    ArrayList<Model> modelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        recyclerView=findViewById(R.id.cat_RecyclerView);
        Intent intent=getIntent();
        String query=intent.getStringExtra("news");
        NewsTask newsTask=new NewsTask();
        newsTask.execute(query);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    class NewsTask extends AsyncTask<String, Void, ArrayList<Model>> {

        @Override
        protected ArrayList<Model> doInBackground(String... strings) {

            newsApiClient.getEverything(
                    new EverythingRequest.Builder()
                            .q(strings[0])
                            .build(),
                    new NewsApiClient.ArticlesResponseCallback() {
                        @Override
                        public void onSuccess(ArticleResponse response) {
                            for(Article article:response.getArticles())
                            {
                                Model model=new Model();
                                model.setTitle(article.getTitle());
                                model.setAuthor(article.getAuthor());
                                model.setDescription(article.getDescription());
                                model.setUrl(article.getUrl());
                                model.setUrlimg(article.getUrlToImage());
                                model.setPublish(article.getPublishedAt());
                                modelArrayList.add(model);
                            }
//                            Article article = response.getArticles().get(0);
//                            Model model = new Model();
//                            model.setTitle(response.getArticles().get(0).getTitle());
//                            model.setAuthor(response.getArticles().get(0).getAuthor());
//                            model.setDescription(response.getArticles().get(0).getDescription());
//                            model.setUrl(response.getArticles().get(0).getUrl());
//                            model.setUrlimg(response.getArticles().get(0).getUrlToImage());
//                            model.setPublish(response.getArticles().get(0).getPublishedAt());
//                            Toast.makeText(MainActivity.this, model.getTitle(), Toast.LENGTH_SHORT).show();
//                            modelArrayList.add(model);
                            Adapter adapter = new Adapter(getApplicationContext(), modelArrayList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "" + modelArrayList.size(), Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            return modelArrayList;

        }

        @Override
        protected void onPostExecute(ArrayList<Model> models) {

        }

    }
}