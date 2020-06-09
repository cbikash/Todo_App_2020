package np.com.cbikas.todoapp2020.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;


@Database(entities={TodoEntity.class},version = 1, exportSchema = false)
@TypeConverters(DateConversion.class)
public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase instance;
    public abstract TodoDao todoDao();

    public static synchronized TaskDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),TaskDatabase.class,"task_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{

        private TodoDao todoDao;

        public PopulateDbAsyncTask(TaskDatabase db) {
            this.todoDao = db.todoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
          todoDao.insert(new TodoEntity("Task1","Task1",2, new Date(2020,3,20)));
          todoDao.insert(new TodoEntity("Task2","Task2",1, new Date(2020,3,21)));
          todoDao.insert(new TodoEntity("Task3","Task3",3, new Date(2020,3,22)));
          return null;
        }
    }




}




























