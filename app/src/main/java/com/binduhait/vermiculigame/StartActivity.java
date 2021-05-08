package com.binduhait.vermiculigame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class StartActivity extends AppCompatActivity {

    ImageButton play, more, shop;
    TextView how;
    TextView tv_coins;
    TextView gold_coins;
    int coins, gold_coin;

    LottieAnimationView animationView,covid_animation, covid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //full screnn

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        animationView = findViewById(R.id.animation);
        covid = findViewById(R.id.covid);
        covid_animation = findViewById(R.id.covid_animation);

        play = (ImageButton) findViewById(R.id.play);
        more = (ImageButton) findViewById(R.id.more);
        shop = (ImageButton) findViewById(R.id.shop);
        how = (TextView) findViewById(R.id.how_to_play);

        tv_coins = (TextView) findViewById(R.id.tv_coins);
        gold_coins = (TextView) findViewById(R.id.gold_coins);

        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        coins = settings.getInt("COINS", 0);
        gold_coin = settings.getInt("GOLD_COINS", 0);

        tv_coins.setText(""+coins);
        gold_coins.setText(""+gold_coin);


        covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                covid_animation.setVisibility(View.VISIBLE);
                covid.setVisibility(View.INVISIBLE);
                covid_animation.playAnimation();

                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(2000); // after 1 sec open other activity or in our case StartActivity
                            startActivity(new Intent(getApplicationContext(), CovidActivity.class));
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                };
                thread.start();
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationView.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                animationView.playAnimation();

                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(1000); // after 1 sec open other activity or in our case StartActivity
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                };
                thread.start();
            }

                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                finish();

        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationView.setVisibility(View.VISIBLE);
                shop.setVisibility(View.VISIBLE);
                animationView.playAnimation();

                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(1000); // after 1 sec open other activity or in our case StartActivity
                            startActivity(new Intent(getApplicationContext(), ShopActivity.class));
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                };
                thread.start();
//                startActivity(new Intent(StartActivity.this, ShopActivity.class));
//                finish();
            }
        });

        how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.setVisibility(View.VISIBLE);
                how.setVisibility(View.VISIBLE);
                animationView.playAnimation();

                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(1000); // after 1 sec open other activity or in our case StartActivity
                            startActivity(new Intent(getApplicationContext(), HowActivity.class));
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                };
                thread.start();
//                startActivity(new Intent(StartActivity.this,HowActivity.class));
//                finish();
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(StartActivity.this, more);
                popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        Intent intent, chooser;
                        int id = menuItem.getItemId();
                        if (id == R.id.feedback){
                            intent = new Intent(Intent.ACTION_SEND);
                            intent.setData(Uri.parse("mailto:"));
                            String[] to = {"hitebindu2002@gmail.com"}; // my email
                            intent.putExtra(Intent.EXTRA_EMAIL, to);
                            intent.setType("message/rfc822");
                            chooser = Intent.createChooser(intent, "Send Feedback");
                            startActivity(chooser);
                        }

                        if (id == R.id.share){
                            intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Pica");
                            String sAux = "\n Let me recommend you this Game \n\n";
                            sAux = sAux+ "https://twitter.com/BinduHite \n\n";
                            intent.putExtra(Intent.EXTRA_TEXT, sAux);
                            startActivity(Intent.createChooser(intent, "Share"));
                        }
                        if (id == R.id.covid_data){
                            startActivity(new Intent(getApplicationContext(), CovidActivity.class));
                            finish();
                        }

                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN){
            switch (event.getKeyCode()){
                case  KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }
        return  super.dispatchKeyEvent(event);
    }
}