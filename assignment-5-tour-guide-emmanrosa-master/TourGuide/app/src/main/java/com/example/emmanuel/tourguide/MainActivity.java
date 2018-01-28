package com.example.emmanuel.tourguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find the View that shows the city hall category
        TextView ch = (TextView) findViewById(R.id.textView2);

        // Set a click listener on that View
        ch.setOnClickListener(new View.OnClickListener() {

            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link cityHallActivity}
                Intent chIntent = new Intent(MainActivity.this, cityHallActivity.class);

                // Start the new activity
                startActivity(chIntent);
            }
        });
        // Find the View that shows the education category
        TextView edu = (TextView) findViewById(R.id.textView3);

        // Set a click listener on that View
        edu.setOnClickListener(new View.OnClickListener() {

            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link eduActivity}
                Intent eduIntent = new Intent(MainActivity.this, eduActivity.class);

                // Start the new activity
                startActivity(eduIntent);
            }
        });

        // Find the View that shows the hospital category
        TextView hosp = (TextView) findViewById(R.id.textView4);

        // Set a click listener on that View
        hosp.setOnClickListener(new View.OnClickListener() {

            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link HostActivity}
                Intent hosIntent = new Intent(MainActivity.this, HostActivity.class);

                // Start the new activity
                startActivity(hosIntent);
            }
        });

        // Find the View that shows the parks category
        TextView park = (TextView) findViewById(R.id.textView5);

        // Set a click listener on that View
        park.setOnClickListener(new View.OnClickListener() {

            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link ParkActivity}
                Intent parkIntent = new Intent(MainActivity.this, ParkActivity.class);

                // Start the new activity
                startActivity(parkIntent);
            }
        });

        // Find the View that shows the restaurants category
        TextView rest = (TextView) findViewById(R.id.textView6);

        // Set a click listener on that View
        rest.setOnClickListener(new View.OnClickListener() {

            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link restActivity}
                Intent restIntent = new Intent(MainActivity.this, restActivity.class);

                // Start the new activity
                startActivity(restIntent);
            }
        });

    }
}
