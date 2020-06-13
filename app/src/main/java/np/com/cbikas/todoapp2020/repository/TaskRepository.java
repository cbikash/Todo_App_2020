package np.com.cbikas.todoapp2020.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;

import np.com.cbikas.todoapp2020.data.TaskDatabase;
import np.com.cbikas.todoapp2020.data.TodoDao;
import np.com.cbikas.todoapp2020.data.TodoEntity;

public class TaskRepository {

    private TodoDao todoDao;
    private LiveData<List<TodoEntity>> allTask;
    private LiveData<List<TodoEntity>> oldTask;
    private LiveData<List<TodoEntity>> newTask;

    public TaskRepository(Application application){
        TaskDatabase database= TaskDatabase.getInstance(application);
        todoDao =database.todoDao();
        allTask =todoDao.loadOldTask();
        oldTask=todoDao.loadOldTask();
        newTask=todoDao.loadUpcomingTask();

    }

    public void insert(TodoEntity todoEntity){
        new InsertTaskAsyncTask(todoDao).execute(todoEntity);

    }
    public void update(TodoEntity todoEntity){
        new UpdateTaskAsyncTask(todoDao).execute(todoEntity);

    }
    public void delete(TodoEntity todoEntity){
      new DeleteTaskAsyncTask(todoDao).execute(todoEntity);

    }
    public  void deleteAllTask(){
        new DeleteAllTaskAsyncTask(todoDao).execute();

    }



    public LiveData<List<TodoEntity>> getAllTask() {
        return allTask;
    }

    public LiveData<List<TodoEntity>> getNewTask() {
        return newTask;
    }

    public LiveData<List<TodoEntity>> getOldTask() {
        return oldTask;
    }


    private static class InsertTaskAsyncTask extends AsyncTask<TodoEntity, Void,Void>{
        private TodoDao todoDao;

        public InsertTaskAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(TodoEntity... todoEntities) {
            todoDao.insert(todoEntities[0]);

            return null;
        }
    }


  private static class UpdateTaskAsyncTask extends AsyncTask<TodoEntity, Void,Void>{

        private TodoDao todoDao;

      public UpdateTaskAsyncTask(TodoDao todoDao) {
          this.todoDao = todoDao;
      }

      @Override
      protected Void doInBackground(TodoEntity... todoEntities) {
          todoDao.update(todoEntities[0]);
          return null;
      }
  }

    private static class DeleteTaskAsyncTask extends AsyncTask<TodoEntity, Void,Void>{

        private TodoDao todoDao;

        public DeleteTaskAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(TodoEntity... todoEntities) {
            todoDao.delete(todoEntities[0]);
            return null;
        }
    }

    private static class DeleteAllTaskAsyncTask extends AsyncTask<Void, Void,Void>{

        private TodoDao todoDao;

        public DeleteAllTaskAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.deleteAllTask();
            return null;
        }
    }




}
