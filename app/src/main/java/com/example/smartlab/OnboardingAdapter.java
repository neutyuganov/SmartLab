package com.example.smartlab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>{

    ArrayList<OnboardingItem> viewPagerItemArrayList;

    public OnboardingAdapter(ArrayList<OnboardingItem> onboardingItems) {
        this.viewPagerItemArrayList = viewPagerItemArrayList;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_onboard, parent, false);
        return new OnboardingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        OnboardingItem viewPagerItem =  viewPagerItemArrayList.get(position);


        holder.imageOnboarding.setImageResource(viewPagerItem.image);
        holder.textTitle.setText(viewPagerItem.title);
        holder.textOnboarding.setText(viewPagerItem.description);

    }

    @Override
    public int getItemCount() {
        return viewPagerItemArrayList.size();
    }

    public class OnboardingViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle;
        TextView textOnboarding;
        ImageView imageOnboarding;

        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.title);
            textOnboarding = itemView.findViewById(R.id.text);
            imageOnboarding = itemView.findViewById(R.id.image);
        }
    }
}
