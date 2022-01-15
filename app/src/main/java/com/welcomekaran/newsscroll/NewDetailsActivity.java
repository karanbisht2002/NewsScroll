package com.welcomekaran.newsscroll;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewDetailsActivity extends AppCompatActivity {

    String title,desc,image,content,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_details);
        title=getIntent().getStringExtra("title");
        desc=getIntent().getStringExtra("desc");
        image=getIntent().getStringExtra("image");
        content=getIntent().getStringExtra("contant");
        url=getIntent().getStringExtra("url");

        TextView headlines = findViewById(R.id.text_view_headlines);
        TextView description = findViewById(R.id.textview_news_description);
        ImageView imageView = findViewById(R.id.image_id_newsdetails);
        TextView contant=findViewById(R.id.text_view_contant);
        Button  readmore=findViewById(R.id.button_read_more);



        headlines.setText(title);
        description.setText(desc);
        Picasso.get().load(image).into(imageView);
        contant.setText(content);
        readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
               startActivity(intent);
            }
        });






    }
}