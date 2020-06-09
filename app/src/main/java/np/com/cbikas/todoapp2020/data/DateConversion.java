package np.com.cbikas.todoapp2020.data;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConversion {
    @TypeConverter
    public static Date toDate(Long timeStamp){
        return timeStamp == null ? null : new Date(timeStamp);
    }

    @TypeConverter
    public static Long toTimeStamp(Date date){return  date == null ? null : date.getTime();}
}
