package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    Button Everything, Business, Entertainment, Health,
            Science, Sports, Technology;
    NewsApiClient newsApiClient = new NewsApiClient("840a317ab9944f94bb82811f863c1599");
    ArrayList<Model> modelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.RecyclerView);
        Everything = findViewById(R.id.btn_Everything);
        Business = findViewById(R.id.btn_Business);
        Entertainment = findViewById(R.id.btn_Entertainment);
        Health = findViewById(R.id.btn_Health);
        Science = findViewById(R.id.btn_Science);
        Sports = findViewById(R.id.btn_Sports);
        Technology = findViewById(R.id.btn_Technology);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewsTask newsTask = new NewsTask();
        newsTask.execute();

        Everything.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Categories.class);
                intent.putExtra("news", "Everything");
                startActivity(intent);
            }
        });
        Business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Categories.class);
                intent.putExtra("news", "Business");
                startActivity(intent);
            }
        });
        Entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Categories.class);
                intent.putExtra("news", "Entertainment");
                startActivity(intent);

            }
        });
        Health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Categories.class);
                intent.putExtra("news", "Health");
                startActivity(intent);
            }
        });
        Science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Categories.class);
                intent.putExtra("news", "Science");
                startActivity(intent);
            }
        });
        Sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Categories.class);
                intent.putExtra("news", "Sports");
                startActivity(intent);
            }
        });
        Technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Categories.class);
                intent.putExtra("news", "Technology");
                startActivity(intent);
            }
        });
    }


    class NewsTask extends AsyncTask<Void, Void, ArrayList<Model>> {

        @Override
        protected void onPostExecute(ArrayList<Model> models) {

        }

        @Override
        protected ArrayList<Model> doInBackground(Void... voids) {


            newsApiClient.getEverything(
                    new EverythingRequest.Builder()
                            .q("Business")
                            .build(),
                    new NewsApiClient.ArticlesResponseCallback() {
                        @Override
                        public void onSuccess(ArticleResponse response) {
                            for (Article article : response.getArticles()) {
                                Model model = new Model();
                                model.setTitle(article.getTitle());
                                model.setAuthor(article.getAuthor());
                                model.setDescription(article.getDescription());
                                model.setUrl(article.getUrl());
                                model.setUrlimg(article.getUrlToImage());
                                model.setPublish(article.getPublishedAt());
                                model.setId(article.getSource().getId());
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
                            Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            return modelArrayList;
        }
    }
}