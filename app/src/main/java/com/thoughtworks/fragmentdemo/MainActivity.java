package com.thoughtworks.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button androidButton;
    private Button javaButton;
    private FragmentManager fragmentManager;
    private Fragment androidFragment;
    private Fragment javaFragment;
    private boolean isAndroidButtonActivated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidButton = findViewById(R.id.android_button);
        javaButton = findViewById(R.id.java_button);

        androidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAndroidButton();
            }
        });

        javaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickJavaButton();
            }
        });

        androidFragment = new AndroidFragment();
        javaFragment = new JavaFragment();
        fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, androidFragment);
        fragmentTransaction.commit();
        androidButton.setActivated(true);
        this.isAndroidButtonActivated = true;
    }

    private void clickJavaButton() {
        if(this.isAndroidButtonActivated) {
            androidButton.setActivated(false);
            this.isAndroidButtonActivated = false;
            javaButton.setActivated(true);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, javaFragment);
            fragmentTransaction.commit();
        }
    }

    private void clickAndroidButton() {
        if(!this.isAndroidButtonActivated) {
            javaButton.setActivated(false);
            androidButton.setActivated(true);
            this.isAndroidButtonActivated = true;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, androidFragment);
            fragmentTransaction.commit();
        }
    }
}
