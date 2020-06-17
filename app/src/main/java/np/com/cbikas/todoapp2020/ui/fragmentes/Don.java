package np.com.cbikas.todoapp2020.ui.fragmentes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import np.com.cbikas.todoapp2020.ui.recycle_view_adapter.DonAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Don extends Fragment {



    private TaskViewModel taskViewModel;
    RecyclerView recyclerView;


    public Don() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_don, container, false);



    }






    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        final DonAdapter donAdapter=new DonAdapter();
        recyclerView.setAdapter(donAdapter);





        taskViewModel= ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getOldTask().observe(getViewLifecycleOwner(), new Observer<List<TodoEntity>>() {
            @Override
            public void onChanged(List<TodoEntity> todoEntities) {

                donAdapter.setTask(todoEntities);

                //Toast.makeText(getActivity(),"AllTask",Toast.LENGTH_SHORT).show();
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                taskViewModel.delete(donAdapter.gettaskAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(),"Note Deleted",Toast.LENGTH_SHORT).show();
            }
        })
                .attachToRecyclerView(recyclerView);

              donAdapter.setOnItemClickListener(new DonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TodoEntity todoEntity) {
                Intent intent=new Intent(getActivity(), AddEditTask.class);
                intent.putExtra(AddEditTask.EXTRA_ID,todoEntity.getId());
                intent.putExtra(AddEditTask.EXTRA_TITLE,todoEntity.getTitle());
                intent.putExtra(AddEditTask.EXTRA_DESCRIPTION,todoEntity.getDescription());

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String update = sdf.format(todoEntity.getUpdate_date());

                intent.putExtra(AddEditTask.EXTRA_DATE,update);

                intent.putExtra(AddEditTask.EXTRA_PRIORITY,todoEntity.getPriority());
                startActivityForResult(intent, MainActivity.EDIT_NOTE_REQUEST);
            }
        });


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
    }
}
