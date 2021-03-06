package com.ucm.proyecto_ucm_tmi2019_g5.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OpenGLActivity extends AppCompatActivity {

    private OpenGLView openGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openGLView = new OpenGLView(this);
        setContentView(openGLView);
        openGLView.requestFocus();
        openGLView.setFocusableInTouchMode(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        openGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        openGLView.onResume();
    }
}