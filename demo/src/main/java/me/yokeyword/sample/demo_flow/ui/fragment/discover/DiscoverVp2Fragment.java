package me.yokeyword.sample.demo_flow.ui.fragment.discover;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;
import me.yokeyword.sample.R;
import me.yokeyword.sample.demo_flow.adapter.DiscoverVp2FragmentAdapter;
import me.yokeyword.sample.demo_flow.base.BaseMainFragment;


public class DiscoverVp2Fragment extends BaseMainFragment {

    private TabLayoutMediator mMediator;

    public static DiscoverVp2Fragment newInstance() {
        return new DiscoverVp2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover_vp2, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        Toolbar mToolbar = view.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.discover);
        initToolbarNav(mToolbar);

        TabLayout mTabLayout = view.findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.recommend));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.hot));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.favorite));

        ViewPager2 mViewPager = view.findViewById(R.id.viewPager);
        String[] titles = new String[] {getString(R.string.recommend), getString(R.string.hot), getString(R.string.favorite)};
        mViewPager.setAdapter(new DiscoverVp2FragmentAdapter(getChildFragmentManager(), this.getLifecycle(), titles.length));

        mMediator = new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
            tab.setText(titles[position]);
        });
        mMediator.attach();
    }

    @Override
    public void onDestroyView() {
        mMediator.detach();
        super.onDestroyView();
    }
}
