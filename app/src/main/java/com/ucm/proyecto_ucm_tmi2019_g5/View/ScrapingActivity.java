package com.ucm.proyecto_ucm_tmi2019_g5.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ucm.proyecto_ucm_tmi2019_g5.Controller.PhotoController;
import com.ucm.proyecto_ucm_tmi2019_g5.R;
import com.ucm.proyecto_ucm_tmi2019_g5.Util.FileUtil;
import com.ucm.proyecto_ucm_tmi2019_g5.Util.Scraping;
import com.ucm.proyecto_ucm_tmi2019_g5.Util.ScreenshotUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.ucm.proyecto_ucm_tmi2019_g5.Util.Scraping.*;
import static java.lang.Thread.sleep;


public class ScrapingActivity extends AppCompatActivity {

    TextView tvMenu;
    private ImageView imageView;
    private FileUtil fu;
    private ImageView imageViewShowScreenshot;
    private View actualView;
    private PhotoController pc = new PhotoController();
    String restaurantName = null;
    String menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scraping);
        Bundle extras = getIntent().getExtras();
        restaurantName = extras.getString("restaurantName");
        System.out.println("NOME DEL RISTORANTE: " + restaurantName);

        menu = pc.getRestaurantMenu(restaurantName);

        System.out.println("\n\nLISTA PIATTI MENU: " + menu);

        imageViewShowScreenshot = findViewById(R.id.imageViewShowScreenshot);
        actualView = findViewById(android.R.id.content);
        tvMenu = findViewById(R.id.tv_menu);
        tvMenu.setText(menu);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        actualView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                actualView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                actualView.getHeight(); //height is ready
                Bitmap bitmap = ScreenshotUtil.getInstance().takeScreenshotForView(actualView); // Take ScreenshotUtil for activity
                imageViewShowScreenshot.setImageBitmap(bitmap);
                String path = Environment.getExternalStorageDirectory().toString() + "/ScreenshotTest.png";
                System.out.println("PERCORSO DEL FILE = " + path);
                FileUtil.getInstance().storeBitmap(bitmap, path);
                GoToGestureActivity(path);
                //GoToTextureActivity(path);
            }
        });
    }

    private void GoToTextureActivity(String path) {
        Intent intent = new Intent(getApplicationContext(), TextureActivity.class);
        intent.putExtra("path", path);
        startActivity(intent);
    }

    private void GoToGestureActivity(String path) {
        Intent intent = new Intent(getApplicationContext(), GestureActivity.class);
        intent.putExtra("path", path);
        startActivity(intent);
    }
}

/*
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    menu.setText(doScraping());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
*/