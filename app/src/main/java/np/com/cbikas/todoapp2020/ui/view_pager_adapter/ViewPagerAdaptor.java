package np.com.cbikas.todoapp2020.ui.view_pager_adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdaptor extends FragmentPagerAdapter {

    private final  ArrayList<Fragment> fragmentslist=new ArrayList<>();
    private final ArrayList<String> fragmentTitle = new ArrayList<>();

    public ViewPagerAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentslist.get(position);
    }

    @Override
    public int getCount() {
        return fragmentslist.size();
    }


    public void addFragment(Fragment fragment,String title){
        fragmentslist.add(fragment);
        fragmentTitle.add(title);

    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

            return  fragmentTitle.get(position);

    }
}




























