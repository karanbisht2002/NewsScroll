package com.welcomekaran.newsscroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoriesRVAdapter.CategoriesClickInterface {
//api key ->  c310103670034a7a98bde5d8d682c1ec

    private RecyclerView newsRv,categoriesRV;
    private ProgressBar progressBar;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategorieyRVModal> categoriesRVAdapterArrayList;
    private CategoriesRVAdapter categoriesRVAdapter;
    private  NewsRVAdapter newsRVAdapter;
    CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRv=findViewById(R.id.recycleview_news);
        categoriesRV=findViewById(R.id.recycleview_categories);
        articlesArrayList=new ArrayList<>();
        categoriesRVAdapterArrayList=new ArrayList<>();
        categoriesRVAdapter=new CategoriesRVAdapter(categoriesRVAdapterArrayList,this,this::onCategoriesclick);
        newsRVAdapter=new NewsRVAdapter(articlesArrayList,this);
        newsRv.setLayoutManager(new LinearLayoutManager(this));
        newsRv.setAdapter(newsRVAdapter);
        categoriesRV.setAdapter(categoriesRVAdapter);
        getCatogries();
        getNews("ALL");
        newsRVAdapter.notifyDataSetChanged();
    }

    private void getCatogries()
    {
        categoriesRVAdapterArrayList.add(new CategorieyRVModal("ALL","https://play-lh.googleusercontent.com/xt-x1NVR2mc0dti19d9pHaIC6wUL7xiveEZ5GJrX904dAYZo_NaMZyWo0K_96QGuQ_qj"));
        categoriesRVAdapterArrayList.add(new CategorieyRVModal("Technology","https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8dGVjaG5vbG9neXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=400&q=60"));
        categoriesRVAdapterArrayList.add(new CategorieyRVModal("Science","https://media.istockphoto.com/photos/vaccine-in-laboratory-flu-shot-and-covid19-vaccination-picture-id1289345741?b=1&k=20&m=1289345741&s=170667a&w=0&h=oG8iaDNP4rOLSgXWfeSziU3Vyu6KJS9Hn2ORohzSsRg="));
        categoriesRVAdapterArrayList.add(new CategorieyRVModal("Sports","https://media.istockphoto.com/photos/various-sport-equipments-on-grass-picture-id949190736?b=1&k=20&m=949190736&s=170667a&w=0&h=f3ofVqhbmg2XSVOa3dqmvGtHc4VLA_rtbboRGaC8eNo="));
        categoriesRVAdapterArrayList.add(new CategorieyRVModal("Entertainment","https://media.istockphoto.com/photos/popcorn-and-clapperboard-picture-id1191001701?b=1&k=20&m=1191001701&s=170667a&w=0&h=uVqDpnXNtnfbhB-F4sWac_t3oL_YSrDuHeCKdaJGS3U="));
        categoriesRVAdapterArrayList.add(new CategorieyRVModal("Health","https://images.unsplash.com/photo-1532938911079-1b06ac7ceec7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8aGVhbHRofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=400&q=60"));
         categoriesRVAdapter.notifyDataSetChanged();
    }

    private void getNews(String categories)
    {
       // progressBar.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String myapi="https://newsapi.org/v2/top-headlines?category=General&apiKey=b47fc9b4e93d47bbb35075c2e0085e2c";
        String category1="https://newsapi.org/v2/top-headlines?country=in&category="+categories+"&apiKey=b47fc9b4e93d47bbb35075c2e0085e2c";
        String url="https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=b47fc9b4e93d47bbb35075c2e0085e2c";
        String base_url="https://newsapi.org";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI=retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(categories.equals("ALL"))
        {
            call=retrofitAPI.getAllNews(myapi);
        }
        else
        {
            call=retrofitAPI.getAllNews(category1);
        }

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                 NewsModal newsModal=response.body();
                // progressBar.setVisibility(View.GONE);
                 ArrayList<Articles> articles =newsModal.getArticles();
                 for(int i=0;i<articles.size();i++)
                 {
                     articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrl(),articles.get(i).getUrlToImage(),articles.get(i).getPublishedAt(),articles.get(i).getContent()));
                 }

                 newsRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed to get news",Toast.LENGTH_LONG);
            }
        });
    }
    @Override
    public void onCategoriesclick(int position) {
              String cat=categoriesRVAdapterArrayList.get(position).getCategory();
              getNews(cat);

    }
}