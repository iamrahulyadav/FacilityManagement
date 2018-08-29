package com.shoaibnwar.facilitymanagement.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.shoaibnwar.facilitymanagement.R;

import java.util.HashMap;

public class HomeActivity extends BaseActvitvityForDrawer implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private View parentView;
    private LinearLayout ll_room_booking, ll_order_food, ll_room_service, ll_book_cap;
    private RelativeLayout tv_show_more;
    private SliderLayout mDemoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_home, null, false);
        mDrawerLayout.addView(contentView, 0);
        // setContentView(R.layout.activity_home);

        init();
        demoSlider();
        showMaoreClickHandler();
        buttonsClickHandler();
    }

    private void init()
    {
        ll_room_booking  = (LinearLayout) findViewById(R.id.ll_room_booking);
        ll_order_food  = (LinearLayout) findViewById(R.id.ll_order_food);
        ll_room_service  = (LinearLayout) findViewById(R.id.ll_room_service);
        ll_book_cap  = (LinearLayout) findViewById(R.id.ll_book_cap);
        tv_show_more = (RelativeLayout) findViewById(R.id.tv_show_more);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

    }

    private void demoSlider()
    {

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hotel Safari",R.drawable.hotel_with_pool);
        file_maps.put("PC Hotel",R.drawable.hotel_image);
        file_maps.put("Hotel One",R.drawable.hotel_with_pool);
        file_maps.put("Avari Lahore", R.drawable.hotel_with_pool);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(HomeActivity.this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

    }

    private void buttonsClickHandler()
    {
        //roo booking click listsner
        ll_room_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SubmisionActivity.class);
                intent.putExtra("item_id", "1");
                startActivity(intent);
            }
        });

        //order food click listener
        ll_order_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, SubmisionActivity.class);
                intent.putExtra("item_id", "2");
                startActivity(intent);
            }
        });

        //room service click listener
        ll_room_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, SubmisionActivity.class);
                intent.putExtra("item_id", "3");
                startActivity(intent);
            }
        });

        ll_book_cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, SubmisionActivity.class);
                intent.putExtra("item_id", "4");
                startActivity(intent);
            }
        });

    }
    private void showMaoreClickHandler()
    {
        tv_show_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeMoreOptions.class));

            }
        });
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}

