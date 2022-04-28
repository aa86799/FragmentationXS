package me.yokeyword.sample.demo_flow.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import me.yokeyword.sample.demo_flow.ui.fragment.discover.PagerChildFragment;

/**
 * Created by YoKeyword on 16/2/5.
 */
public class DiscoverVp2FragmentAdapter extends FragmentStateAdapter {

    private int mCount;

    public DiscoverVp2FragmentAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, int count) {
        super(fragmentManager, lifecycle);
        this.mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return PagerChildFragment.newInstance(0);
        } else if (position == 1) {
            return PagerChildFragment.newInstance(1);
        } else {
            return PagerChildFragment.newInstance(2);
        }
    }

    @Override
    public int getItemCount() {
        return mCount;
    }
}
