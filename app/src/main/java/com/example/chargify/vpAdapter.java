package com.example.chargify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class vpAdapter extends PagerAdapter {
Context context;

int img[]={
        R.drawable.head1,
        R.drawable.chargeanim,
        R.drawable.settingonboarding
    };

int heads[]={
        R.string.get_started_with_chargify,
        R.string.dynamic_charging_visuals,
        R.string.real_time_battery_insights
    };

int desc[]={
        R.string.description,
        R.string.dynamic_charging_visuals_description,
        R.string.real_time_battery_insights_description
    };

public vpAdapter(Context context){
    this.context=context;
}
    @Override
    public int getCount() {
        return heads.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.onboard_slider_layout,container,false);

        ImageView slidetitleimage=(ImageView) view.findViewById(R.id.headingimage);
        TextView slideheading=(TextView) view.findViewById(R.id.tvheading);
        TextView slidetext=(TextView) view.findViewById(R.id.tvslidertext);
        slidetitleimage.setImageResource(img[position]);
        slideheading.setText(heads[position]);
        slidetext.setText(desc[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
