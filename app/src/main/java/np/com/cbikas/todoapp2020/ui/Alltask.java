package np.com.cbikas.todoapp2020.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import np.com.cbikas.todoapp2020.R;
import np.com.cbikas.todoapp2020.data.TodoEntity;
import np.com.cbikas.todoapp2020.taskViewModel.TaskViewModel;
import np.com.cbikas.todoapp2020.ui.recycle_view.AllTaskAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Alltask extends Fragment {

    private TaskViewModel taskViewModel;
    RecyclerView recyclerView;


    public Alltask() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alltask, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        final AllTaskAdapter allTaskAdapter=new AllTaskAdapter();
        recyclerView.setAdapter(allTaskAdapter);





        taskViewModel= ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getAllTask().observe(getViewLifecycleOwner(), new Observer<List<TodoEntity>>() {
            @Override
            public void onChanged(List<TodoEntity> todoEntities) {

               allTaskAdapter.setTask(todoEntities);

//                Toast.makeText(getActivity(),"AllTask",Toast.LENGTH_SHORT).show();
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
              taskViewModel.delete(allTaskAdapter.gettaskAt(viewHolder.getAdapterPosition()));
           Toast.makeText(getActivity(),"Note Deleted",Toast.LENGTH_SHORT).show();
          }
      })
              .attachToRecyclerView(recyclerView);

      allTaskAdapter.setOnItemClickListener(new AllTaskAdapter.OnItemClickListener() {
          @Override
          public void onItemClick(TodoEntity todoEntity) {
              Intent intent=new Intent(getActivity(), AddEditTask.class);
              intent.putExtra(AddEditTask.EXTRA_ID,todoEntity.getId());
              intent.putExtra(AddEditTask.EXTRA_TITLE,todoEntity.getTitle());
              intent.putExtra(AddEditTask.EXTRA_DESCRIPTION,todoEntity.getDescription());
              intent.putExtra(AddEditTask.EXTRA_PRIORITY,todoEntity.getPriority());
              startActivityForResult(intent,MainActivity.EDIT_NOTE_REQUEST);
          }
      });


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}
