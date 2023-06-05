package com.example.smartlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    int image[] = {
            R.drawable.image_analyzes,
            R.drawable.images_notifications,
            R.drawable.image_monitoring
    };

    int heading[] = {
            R.string.title_analyzes,
            R.string.title_notifications,
            R.string.title_monitoring
    };

    int description[] = {
            R.string.text_analyzes,
            R.string.text_notifications,
            R.string.text_monitoring
    };

    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container, false);

        ImageView slideTitleImage = (ImageView) view.findViewById(R.id.image);
        TextView slideHeading = (TextView) view.findViewById(R.id.title);
        TextView slideDescription = (TextView) view.findViewById(R.id.text);

        slideTitleImage.setImageResource(image[position]);
        slideHeading.setText(heading[position]);
        slideDescription.setText(description[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
