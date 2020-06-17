package np.com.cbikas.todoapp2020.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import android.widget.SearchView;
import android.widget.Toast;


import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import np.com.cbikas.todoapp2020.R;
import np.com.cbikas.todoapp2020.data.TodoEntity;
import np.com.cbikas.todoapp2020.taskViewModel.TaskViewModel;
import np.com.cbikas.todoapp2020.ui.fragmentes.Alltask;
import np.com.cbikas.todoapp2020.ui.fragmentes.Don;
import np.com.cbikas.todoapp2020.ui.fragmentes.NotDoneyet;
import np.com.cbikas.todoapp2020.ui.view_pager_adapter.ViewPagerAdaptor;
import np.com.cbikas.todoapp2020.ui.recycle_view_adapter.AllTaskAdapter;

import androidx.appcompat.widget.Toolbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity{
     Toolbar toolbar1;
    private EditText todaydate;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton addtask;
    public static final int ADD_TASK_REQUEST=1;
    public static final int EDIT_NOTE_REQUEST= 2;
    private TaskViewModel taskViewModel;
    BottomAppBar bottomAppBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar1= findViewById(R.id.mytoolbar1);

        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.myViewpager);

        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        addtask=findViewById(R.id.addtask);
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AddEditTask.class);
                startActivityForResult(intent, ADD_TASK_REQUEST);

            }
        });

//
//        Intent getIntent=getIntent();
//
//        Toast.makeText(this, getIntent.getStringExtra(AddEditTask.EXTRA_TITLE)+" ",Toast.LENGTH_LONG ).show();
//       if(getIntent.getIntExtra(AddEditTask.EXTRA_ID,-1) != -1 ){
//
//          int id=getIntent.getIntExtra(AddEditTask.EXTRA_ID,-1);
//           String title=getIntent.getStringExtra(AddEditTask.EXTRA_TITLE);
//           String description=getIntent.getStringExtra(AddEditTask.EXTRA_DESCRIPTION);
//           int priority=getIntent.getIntExtra(AddEditTask.EXTRA_PRIORITY,1);
//
//           Intent intent=new Intent(MainActivity.this,AddEditTask.class);
//
//           intent.putExtra(AddEditTask.EXTRA_ID,id);
//           intent.putExtra(AddEditTask.EXTRA_TITLE,title);
//           intent.putExtra(AddEditTask.EXTRA_DESCRIPTION,description);
//           intent.putExtra(AddEditTask.EXTRA_PRIORITY,priority);
//          startActivityForResult(intent,EDIT_NOTE_REQUEST);
//       }


        setSupportActionBar(toolbar1);
       getSupportActionBar().setTitle("");
       setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        bottomAppBar=findViewById(R.id.bottomAppBar);
        setSupportActionBar(bottomAppBar);

    }




    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdaptor viewPagerAdaptor=new ViewPagerAdaptor(getSupportFragmentManager());
        viewPagerAdaptor.addFragment(new Alltask(),"All");

        viewPagerAdaptor.addFragment(new NotDoneyet(),"Comming");

        viewPagerAdaptor.addFragment(new Don(),"Don");

        viewPager.setAdapter(viewPagerAdaptor);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Toast.makeText(this,data.getStringExtra(AddEditTask.EXTRA_TITLE)+" ",Toast.LENGTH_LONG).show();
        if(requestCode==ADD_TASK_REQUEST && resultCode == RESULT_OK){
            Date date2=null;
            String title=data.getStringExtra(AddEditTask.EXTRA_TITLE);
            String description=data.getStringExtra(AddEditTask.EXTRA_DESCRIPTION);
            int priority=data.getIntExtra(AddEditTask.EXTRA_PRIORITY,1);
            String date1=data.getStringExtra(AddEditTask.EXTRA_DATE);


            SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");

            try {
                date2=formatter1.parse(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Toast.makeText(this,title+""+description+""+priority+""+date2,Toast.LENGTH_LONG).show();
           TodoEntity todoEntity=new TodoEntity(title,description,priority,date2);
            taskViewModel.insert(todoEntity);
            Toast.makeText(this,"Task Insert",Toast.LENGTH_SHORT).show();




        }else if(resultCode == RESULT_OK) {
            int id= data.getIntExtra(AddEditTask.EXTRA_ID,-1);
            if(id==-1){
                Toast.makeText(this, "Note cant be updated", Toast.LENGTH_SHORT).show();
            return;
            }
            Date date2 =null;
            String title=data.getStringExtra(AddEditTask.EXTRA_TITLE);
            String description=data.getStringExtra(AddEditTask.EXTRA_DESCRIPTION);
            int priority=data.getIntExtra(AddEditTask.EXTRA_PRIORITY,1);
            String date1=data.getStringExtra(AddEditTask.EXTRA_DATE);
            SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");

            try {
                date2=formatter1.parse(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            TodoEntity todoEntity=new TodoEntity(title,description,priority,date2);
            todoEntity.setId(id);
            taskViewModel.update(todoEntity);
            Toast.makeText(this,"Task Updated",Toast.LENGTH_SHORT).show();

        }else
        {
            Toast.makeText(this,"Note Not saved",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();

        menuInflater.inflate(R.menu.menu,menu);

        final AllTaskAdapter allTaskAdapter=new AllTaskAdapter();
        MenuItem searchItem= menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                allTaskAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menudeletAll:
                taskViewModel.deleteAllTask();
                Toast.makeText(this,"All Note Deleted",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuabout:
               dilogShow();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void dilogShow(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("This App was built for saving you daily routine Task /n" +
                "To make remember and alert/n" +
                "Thank you for supporting us");
        alertDialogBuilder.setNegativeButton("Close",
                new DialogInterface.OnClickListener() {
             @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}


























