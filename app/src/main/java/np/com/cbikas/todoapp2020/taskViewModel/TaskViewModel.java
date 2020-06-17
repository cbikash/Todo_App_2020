package np.com.cbikas.todoapp2020.taskViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import np.com.cbikas.todoapp2020.data.TodoEntity;
import np.com.cbikas.todoapp2020.repository.TaskRepository;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository repository;
    private LiveData<List<TodoEntity>> allTask;
    private LiveData<List<TodoEntity>> allTasktitle;
    private LiveData<List<TodoEntity>> allTaskdate;
    private LiveData<List<TodoEntity>> allTaskdescription;
    private LiveData<List<TodoEntity>> newTask;
    private LiveData<List<TodoEntity>> oldTask;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTask = repository.getAllTask();
        oldTask=repository.getOldTask();
        newTask=repository.getNewTask();
        allTasktitle=repository.getAllTasktitle();
        allTaskdate=repository.getAllTaskdate();
        allTaskdescription= repository.getAllTaskdescription();
    }
    public void insert(TodoEntity todoEntity){
        repository.insert(todoEntity);
    }
    public void update(TodoEntity todoEntity){
        repository.update(todoEntity);
    }
    public void delete(TodoEntity todoEntity){
        repository.delete(todoEntity);
    }
    public void deleteAllTask(){
        repository.deleteAllTask();
    }

    public LiveData<List<TodoEntity>> getNewTask() {
        return newTask;
    }

    public LiveData<List<TodoEntity>> getAllTask() {
        return allTask;
    }
    public LiveData<List<TodoEntity>> getAllTasktitle() {
        return allTasktitle;
    }

    public LiveData<List<TodoEntity>> getAllTaskdate() {
        return allTaskdate;
    }

    public LiveData<List<TodoEntity>> getAllTaskdescription() {
        return allTaskdescription;
    }


    public LiveData<List<TodoEntity>> getOldTask()
    {
        return oldTask;
    }
}
