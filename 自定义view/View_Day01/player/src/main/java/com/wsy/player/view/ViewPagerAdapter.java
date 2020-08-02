package com.wsy.player.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    List<View> discLayouts;

    public ViewPagerAdapter(List<View> discLayouts) {
        this.discLayouts = discLayouts;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View discLayout = discLayouts.get(position);
        container.addView(discLayout);
        return discLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(discLayouts.get(position));
    }

    @Override
    public int getCount() {
        return discLayouts.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
