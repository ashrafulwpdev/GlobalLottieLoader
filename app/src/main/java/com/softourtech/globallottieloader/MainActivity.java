package com.softourtech.globallottieloader;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.softourtech.globallottieloader.utils.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showDefaultLoaderButton = findViewById(R.id.showDefaultLoaderButton);
        Button showCustomLoaderButton = findViewById(R.id.showCustomLoaderButton);
        Button hideLoaderButton = findViewById(R.id.hideLoaderButton);

        showDefaultLoaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDefaultLoader();
            }
        });

        showCustomLoaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaderConfig customConfig = new LoaderConfig()
                        .setAnimationResId(R.raw.loading_global) // Change this to your custom animation resource
                        .setWidthDp(100)
                        .setHeightDp(100)
                        .setUseRoundedBox(true)
                        .setOverlayColor(Color.parseColor("#50000000")) // Semi-transparent dark overlay
                        .setChangeJsonColor(true)
                        .setJsonColor(Color.parseColor("#FF5722")) // Orange color
                        .setAnimationSpeed(1.5f);
                showCustomLoader(customConfig);
            }
        });

        hideLoaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideLoader();
            }
        });
    }
}