package np.com.cbikas.todoapp2020.ui.fragmentes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.List;

import np.com.cbikas.todoapp2020.R;
import np.com.cbikas.todoapp2020.data.TodoEntity;
import np.com.cbikas.todoapp2020.taskViewModel.TaskViewModel;
import np.com.cbikas.todoapp2020.ui.activities.AddEditTask;
import np.com.cbikas.todoapp2020.ui.activities.MainActivity;
import np.com.cbikas.todoapp2020.ui.recycle_view_adapter.AllTaskAdapter;
import np.com.cbikas.todoapp2020.ui.recycle_view_adapter.DonAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Alltask extends Fragment {
    private TaskViewModel taskViewModel;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private Toolbar toolbar;
    View v;


    public Alltask() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity main = (MainActivity)getActivity();
        toolbar=main.findViewById(R.id.mytoolbar1);
        main.setSupportActionBar(toolbar);
        v=inflater.inflate(R.layout.fragment_alltask, container, false);
        setUp("defult");
       return v;
    }

    private void setUp(String a) {

        recyclerView = v.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        final AllTaskAdapter allTaskAdapter=new AllTaskAdapter();
        recyclerView.setAdapter(allTaskAdapter);
        String x=a;
        taskViewModel= ViewModelProviders.of(this).get(TaskViewModel.class);
        if(x =="defult"){
           // Toast.makeText(getActivity(),"akdjfdf",Toast.LENGTH_LONG).show();
            taskViewModel.getAllTask().observe(getViewLifecycleOwner(), new
                    Observer<List<TodoEntity>>() {
                        @Override
                        public void onChanged(List<TodoEntity> todoEntities) {

                            allTaskAdapter.submitList(todoEntities);
                        }
                    });


            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    taskViewModel.delete(allTaskAdapter.gettaskAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(getActivity(), "Note Deleted", Toast.LENGTH_SHORT).show();
                }
            })
                    .attachToRecyclerView(recyclerView);

            allTaskAdapter.setOnItemClickListener(new AllTaskAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(TodoEntity todoEntity) {
                    Intent intent = new Intent(getActivity(), AddEditTask.class);
                    intent.putExtra(AddEditTask.EXTRA_ID, todoEntity.getId());
                    intent.putExtra(AddEditTask.EXTRA_TITLE, todoEntity.getTitle());
                    intent.putExtra(AddEditTask.EXTRA_DESCRIPTION, todoEntity.getDescription());

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String update = sdf.format(todoEntity.getUpdate_date());

                    intent.putExtra(AddEditTask.EXTRA_DATE, update);

                    intent.putExtra(AddEditTask.EXTRA_PRIORITY, todoEntity.getPriority());
                    startActivityForResult(intent, MainActivity.EDIT_NOTE_REQUEST);
                }
            });
        }else if(x.equals("date")) {
            // Toast.makeText(getActivity(),"hello",Toast.LENGTH_LONG).show();
            taskViewModel.getAllTaskdate().observe(getViewLifecycleOwner(), new
                    Observer<List<TodoEntity>>() {
                        @Override
                        public void onChanged(List<TodoEntity> todoEntities) {

                            allTaskAdapter.submitList(todoEntities);
                        }
                    });


            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    taskViewModel.delete(allTaskAdapter.gettaskAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(getActivity(), "Note Deleted", Toast.LENGTH_SHORT).show();
                }
            })
                    .attachToRecyclerView(recyclerView);

            allTaskAdapter.setOnItemClickListener(new AllTaskAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(TodoEntity todoEntity) {
                    Intent intent = new Intent(getActivity(), AddEditTask.class);
                    intent.putExtra(AddEditTask.EXTRA_ID, todoEntity.getId());
                    intent.putExtra(AddEditTask.EXTRA_TITLE, todoEntity.getTitle());
                    intent.putExtra(AddEditTask.EXTRA_DESCRIPTION, todoEntity.getDescription());

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String update = sdf.format(todoEntity.getUpdate_date());

                    intent.putExtra(AddEditTask.EXTRA_DATE, update);

                    intent.putExtra(AddEditTask.EXTRA_PRIORITY, todoEntity.getPriority());
                    startActivityForResult(intent, MainActivity.EDIT_NOTE_REQUEST);
                }
            });
            return;
        }else if(x.equals("title")) {
            // Toast.makeText(getActivity(),"hello",Toast.LENGTH_LONG).show();
            taskViewModel.getAllTasktitle().observe(getViewLifecycleOwner(), new
                    Observer<List<TodoEntity>>() {
                        @Override
                        public void onChanged(List<TodoEntity> todoEntities) {

                            allTaskAdapter.submitList(todoEntities);
                        }
                    });


            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    taskViewModel.delete(allTaskAdapter.gettaskAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(getActivity(), "Note Deleted", Toast.LENGTH_SHORT).show();
                }
            })
                    .attachToRecyclerView(recyclerView);

            allTaskAdapter.setOnItemClickListener(new AllTaskAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(TodoEntity todoEntity) {
                    Intent intent = new Intent(getActivity(), AddEditTask.class);
                    intent.putExtra(AddEditTask.EXTRA_ID, todoEntity.getId());
                    intent.putExtra(AddEditTask.EXTRA_TITLE, todoEntity.getTitle());
                    intent.putExtra(AddEditTask.EXTRA_DESCRIPTION, todoEntity.getDescription());

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String update = sdf.format(todoEntity.getUpdate_date());

                    intent.putExtra(AddEditTask.EXTRA_DATE, update);

                    intent.putExtra(AddEditTask.EXTRA_PRIORITY, todoEntity.getPriority());
                    startActivityForResult(intent, MainActivity.EDIT_NOTE_REQUEST);
                }
            });
            return;
        }else if(x.equals("description")) {
            // Toast.makeText(getActivity(),"hello",Toast.LENGTH_LONG).show();
            taskViewModel.getAllTaskdescription().observe(getViewLifecycleOwner(), new
                    Observer<List<TodoEntity>>() {
                        @Override
                        public void onChanged(List<TodoEntity> todoEntities) {

                            allTaskAdapter.submitList(todoEntities);
                        }
                    });


            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    taskViewModel.delete(allTaskAdapter.gettaskAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(getActivity(), "Note Deleted", Toast.LENGTH_SHORT).show();
                }
            })
                    .attachToRecyclerView(recyclerView);

            allTaskAdapter.setOnItemClickListener(new AllTaskAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(TodoEntity todoEntity) {
                    Intent intent = new Intent(getActivity(), AddEditTask.class);
                    intent.putExtra(AddEditTask.EXTRA_ID, todoEntity.getId());
                    intent.putExtra(AddEditTask.EXTRA_TITLE, todoEntity.getTitle());
                    intent.putExtra(AddEditTask.EXTRA_DESCRIPTION, todoEntity.getDescription());

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String update = sdf.format(todoEntity.getUpdate_date());

                    intent.putExtra(AddEditTask.EXTRA_DATE, update);

                    intent.putExtra(AddEditTask.EXTRA_PRIORITY, todoEntity.getPriority());
                    startActivityForResult(intent, MainActivity.EDIT_NOTE_REQUEST);
                }
            });
            return;
        }




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
            menu.clear();

            // Inflate your new menu
            inflater.inflate(R.menu.menu_frag, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.alltitletask_m:
               setUp("title");
                return true;
            case R.id.alldatetask_m:
                setUp("date");
                return true;

            case R.id.alldestask_m:
                setUp("description");
                return true;

            case R.id.alltaskd_m:
                setUp("defult");
                return true;

            case R.id.alltask_m:
                setUp("defult");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
