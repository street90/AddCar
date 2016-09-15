package com.song.addcar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.song.addcarlibrary.AddCarLibiary;
import com.song.addcarlibrary.addCarEndListener;

public class MainActivity extends AppCompatActivity {

    private ImageView ivStart,ivEnd;
    private RelativeLayout rlRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        ivStart = (ImageView) findViewById(R.id.iv_start);


        ivEnd = (ImageView) findViewById(R.id.iv_end);

        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);


        ivStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCarLibiary.AddCar(ivStart, ivEnd, MainActivity.this, rlRoot, 3, new addCarEndListener() {
                    @Override
                    public void end() {

                    }
                });
            }
        });


        ivEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCarLibiary.AddCar(ivEnd, ivStart, MainActivity.this, rlRoot, 3, new addCarEndListener() {
                    @Override
                    public void end() {

                    }
                });
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
