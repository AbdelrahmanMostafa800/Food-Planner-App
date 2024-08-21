package com.example.mealmate.mealdetails.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealmate.R;
import com.example.mealmate.mealdetails.presenter.MealDetailsPresenterImp;
import com.example.mealmate.mealdetails.presenter.MealDetailsPresenterInterface;
import com.example.mealmate.model.meal.Meal;
import com.example.mealmate.model.countriespojo.CountriesList;
import com.example.mealmate.model.countriespojo.Country;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsActivityView{
    RecyclerView recyclerView;
    String mealID;
    MealDetailsPresenterInterface presenter;
    ImageView arrowBack,mealFlag,mealImage;
    TextView mealName,mealDescription;
    WebView mealVideo;
    String iFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        Intent intent = getIntent();
        mealID= intent.getStringExtra("idMeal");
        presenter=new MealDetailsPresenterImp(this);
        presenter.getMealDetails(mealID);

        arrowBack=findViewById(R.id.arrow_back);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView=findViewById(R.id.ingrediantRecycleView);
        mealFlag=findViewById(R.id.mealFlagView);
        mealName=findViewById(R.id.mealNameView);
        mealDescription=findViewById(R.id.describtiosView);
        mealImage=findViewById(R.id.mealImageView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        mealVideo=findViewById(R.id.mealVideoView);

    }

    @Override
    public void showMealDetails(Meal meal) {
        String areaName=meal.getStrArea();
        CountriesList countryList=CountriesList.getInstance();
        String countryImg = countryList.getcountries().stream()
                .filter(country -> country.getStrArea().equals(areaName))
                .map(Country::getstrContryThumb)
                .findFirst()
                .orElse(null);
        Log.d("mealflag", countryImg);
        Glide.with(mealFlag)
                .load(countryImg)
                .into(mealFlag);
        Glide.with(mealImage)
                .load(meal.getStrMealThumb())
                .into(mealImage);
        mealName.setText(meal.getStrMeal());
        mealDescription.setText(meal.getStrInstructions());
        mealDescription.setText(meal.getStrInstructions());
        IngeridiantRecycleAdapter adapter = new IngeridiantRecycleAdapter(meal.getStrIngredients(),meal.getStrMeasures());
        recyclerView.setAdapter(adapter);

//        mealVideo.setVideoPath(meal.getStrYoutube());
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(mealVideo);
//        mediaController.setMediaPlayer(mealVideo);
//        mealVideo.setMediaController(mediaController);
//        mealVideo.start();

//        MediaController mediaController= new MediaController(this);
//        mediaController.setAnchorView(mealVideo);
//        Log.d("uri", meal.getStrYoutube());
//        Uri uri=Uri.parse(meal.getStrYoutube());
//        mealVideo.setMediaController(mediaController);
//        mealVideo.setVideoURI(meal.getStrYoutube());
//        mealVideo.requestFocus();
//        mealVideo.start();
//        String videoUrl = meal.getStrYoutube();
//        String html = "<iframe width=\"100%\" height=\"100%\" src=\"" + videoUrl + "\" frameborder=\"0\" allowfullscreen></iframe>";
//        mealVideo.loadData(html, "text/html", "utf-8");
//        mealVideo.getSettings().setJavaScriptEnabled(true);
//        mealVideo.setWebChromeClient(new WebChromeClient());
        Log.d("url", meal.getStrYoutube());
        if(meal.getStrYoutube()!=null && !meal.getStrYoutube().isEmpty()) {
            String videoId = meal.getStrYoutube().split("=")[1]; // extract the video ID from the YouTube URL
            iFrame = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "  <body>\n" +
                    "    <!-- 1. The <iframe> (and video player) will replace this <div> tag. -->\n" +
                    "    <div id=\"player\"></div>\n" +
                    "\n" +
                    "    <script>\n" +
                    "      // 2. This code loads the IFrame Player API code asynchronously.\n" +
                    "      var tag = document.createElement('script');\n" +
                    "\n" +
                    "      tag.src = \"https://www.youtube.com/iframe_api\";\n" +
                    "      var firstScriptTag = document.getElementsByTagName('script')[0];\n" +
                    "      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
                    "\n" +
                    "      // 3. This function creates an <iframe> (and YouTube player)\n" +
                    "      //    after the API code downloads.\n" +
                    "      var player;\n" +
                    "      function onYouTubeIframeAPIReady() {\n" +
                    "        player = new YT.Player('player', {\n" +
                    "          height: '300',\n" +
                    "          width: '350',\n" +
                    "          videoId: '" + videoId + "',\n" + // use the extracted video ID here
                    "          playerVars: {\n" +
                    "            'playsinline': 1\n" +
                    "          },\n" +
                    "          events: {\n" +
                    "            'onReady': onPlayerReady,\n" +
                    "            'onStateChange': onPlayerStateChange\n" +
                    "          }\n" +
                    "        });\n" +
                    "      }\n" +
                    "\n" +
                    "      // 4. The API will call this function when the video player is ready.\n" +
                    "      function onPlayerReady(event) {\n" +
                    "        event.target.playVideo();\n" +
                    "      }\n" +
                    "\n" +
                    "      // 5. The API calls this function when the player's state changes.\n" +
                    "      //    The function indicates that when playing a video (state=1),\n" +
                    "      //    the player should play for six seconds and then stop.\n" +
                    "      var done = false;\n" +
                    "      function onPlayerStateChange(event) {\n" +
                    "        if (event.data == YT.PlayerState.PLAYING && !done) {\n" +
                    "          setTimeout(stopVideo, 6000);\n" +
                    "          done = true;\n" +
                    "        }\n" +
                    "      }\n" +
                    "      function stopVideo() {\n" +
                    "        player.stopVideo();\n" +
                    "      }\n" +
                    "    </script>\n" +
                    "  </body>\n" +
                    "</html>";
            mealVideo.getSettings().setJavaScriptEnabled(true);
            mealVideo.loadData(iFrame, "text/html", "utf-8");
        }
    }
}