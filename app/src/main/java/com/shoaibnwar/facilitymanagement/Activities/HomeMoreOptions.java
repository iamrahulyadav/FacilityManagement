package com.shoaibnwar.facilitymanagement.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.shoaibnwar.facilitymanagement.R;

public class HomeMoreOptions extends AppCompatActivity {

    RelativeLayout rl_back_arrow;
    RelativeLayout  rl_circule_1, rl_circule_2, rl_circule_3, rl_circule_4, rl_circule_5, rl_circule_6, rl_circule_7,
                    rl_circule_8, rl_circule_9, rl_circule_10, rl_circule_11, rl_circule_12, rl_circule_13, rl_circule_14,
                    rl_circule_15, rl_circule_16, rl_circule_17, rl_circule_18;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_more_options);

        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );

        init();
        onBackArrowClickHandler();
        onClickHandlerForCircules();
    }

    private void init()
    {
        rl_back_arrow = (RelativeLayout) findViewById(R.id.rl_back_arrow);
        rl_circule_1 = (RelativeLayout) findViewById(R.id.rl_circule_1);
        rl_circule_2 = (RelativeLayout) findViewById(R.id.rl_circule_2);
        rl_circule_3 = (RelativeLayout) findViewById(R.id.rl_circule_3);
        rl_circule_4 = (RelativeLayout) findViewById(R.id.rl_circule_4);
        rl_circule_5 = (RelativeLayout) findViewById(R.id.rl_circule_5);
        rl_circule_6 = (RelativeLayout) findViewById(R.id.rl_circule_6);
        rl_circule_7 = (RelativeLayout) findViewById(R.id.rl_circule_7);
        rl_circule_8 = (RelativeLayout) findViewById(R.id.rl_circule_8);
        rl_circule_9 = (RelativeLayout) findViewById(R.id.rl_circule_9);
        rl_circule_10 = (RelativeLayout) findViewById(R.id.rl_circule_10);
        rl_circule_11 = (RelativeLayout) findViewById(R.id.rl_circule_11);
        rl_circule_12 = (RelativeLayout) findViewById(R.id.rl_circule_12);
        rl_circule_13 = (RelativeLayout) findViewById(R.id.rl_circule_13);
        rl_circule_14 = (RelativeLayout) findViewById(R.id.rl_circule_14);
        rl_circule_15 = (RelativeLayout) findViewById(R.id.rl_circule_15);
        rl_circule_16 = (RelativeLayout) findViewById(R.id.rl_circule_16);
        rl_circule_17 = (RelativeLayout) findViewById(R.id.rl_circule_17);
        rl_circule_18 = (RelativeLayout) findViewById(R.id.rl_circule_18);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( R.anim.slide_out_down, R.anim.slide_in_down);
    }

    private void onBackArrowClickHandler()
    {
        rl_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition( R.anim.slide_out_down, R.anim.slide_in_down);
            }
        });
    }

    private void onClickHandlerForCircules()
    {
        rl_circule_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "1");
                startActivity(intent);
            }
        });

        rl_circule_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "2");
                startActivity(intent);
            }
        });

        rl_circule_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "3");
                startActivity(intent);
            }
        });

        rl_circule_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "4");
                startActivity(intent);
            }
        });

        rl_circule_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "5");
                startActivity(intent);
            }
        });

        rl_circule_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "6");
                startActivity(intent);
            }
        });

        rl_circule_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "7");
                startActivity(intent);
            }
        });

        rl_circule_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "8");
                startActivity(intent);
            }
        });

        rl_circule_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "9");
                startActivity(intent);
            }
        });

        rl_circule_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "10");
                startActivity(intent);
            }
        });

        rl_circule_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "11");
                startActivity(intent);
            }
        });

        rl_circule_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "12");
                startActivity(intent);
            }
        });

        rl_circule_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "13");
                startActivity(intent);
            }
        });

        rl_circule_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "14");
                startActivity(intent);
            }
        });

        rl_circule_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "15");
                startActivity(intent);
            }
        });

        rl_circule_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "16");
                startActivity(intent);
            }
        });

        rl_circule_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "17");
                startActivity(intent);
            }
        });

        rl_circule_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMoreOptions.this, SubmisionActivity.class);
                intent.putExtra("item_id", "18");
                startActivity(intent);
            }
        });
    }
}