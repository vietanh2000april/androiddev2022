package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case Constants.FRAGMENT_HANOI:
                return new WeatherAndForecastFragment();
            case Constants.FRAGMENT_PARIS:
                return new WeatherAndForecastFragment();
            case Constants.FRAGMENT_TOULOUSE:
                return new WeatherAndForecastFragment();
        }
        return new WeatherAndForecastFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
