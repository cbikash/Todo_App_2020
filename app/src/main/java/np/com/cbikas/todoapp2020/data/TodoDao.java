package np.com.cbikas.todoapp2020.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Dao
public interface TodoDao {


    @Insert
    void insert(TodoEntity todoEntity);

    @Update
    void update(TodoEntity todoEntity);

    @Delete
    void delete(TodoEntity todoEntity);

    @Query("DELETE FROM todo_task")
    void deleteAllTask();

    @Query("Select * from todo_task order by priority")
    LiveData<List<TodoEntity>> loadAllTodo();

    @Query("Select * from todo_task order by title asc")
    LiveData<List<TodoEntity>> loadAllTodotitle();

    @Query("Select * from todo_task order by update_date")
    LiveData<List<TodoEntity>> loadAllTodoupdate_date();

    @Query("Select * from todo_task order by description")
    LiveData<List<TodoEntity>> loadAllTododescription();

    @Query("Select * from todo_task where update_date < datetime('now')  order by update_date")
    LiveData<List<TodoEntity>> loadOldTask();



    @Query("Select * from todo_task order by update_date desc")
    LiveData<List<TodoEntity>> loadUpcomingTask();







}




















































