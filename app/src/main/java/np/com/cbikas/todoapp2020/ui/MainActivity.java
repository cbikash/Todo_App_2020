package np.com.cbikas.todoapp2020.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import np.com.cbikas.todoapp2020.R;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar1;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton addtask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar1= findViewById(R.id.mytoolbar);
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.myViewpager);

        addtask=findViewById(R.id.addtask);
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddTask.class);
                startActivity(intent);

            }
        });

       setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("");
       setupViewPager(viewPager );
        tabLayout.setupWithViewPager(viewPager);


    }




    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdaptor viewPagerAdaptor=new ViewPagerAdaptor(getSupportFragmentManager());
        viewPagerAdaptor.addFragment(new Alltask(),"All");
        viewPagerAdaptor.addFragment(new NotDoneyet(),"Past");
        viewPagerAdaptor.addFragment(new Don(),"Coming");
        viewPager.setAdapter(viewPagerAdaptor);



    }
}


























