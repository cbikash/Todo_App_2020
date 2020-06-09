package np.com.cbikas.todoapp2020.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "todo_task")
public class TodoEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private int priority;
    private Date update_date;


    public TodoEntity(String title, String description, int priority, Date update_date) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.update_date = update_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Date getUpdate_date() {
        return update_date;
    }
}




