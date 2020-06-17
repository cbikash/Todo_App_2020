package np.com.cbikas.todoapp2020.ui.recycle_view_adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import np.com.cbikas.todoapp2020.R;
import np.com.cbikas.todoapp2020.data.TodoEntity;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.AlltaskHolder> implements Filterable {
    private List<TodoEntity> todoEntities=new ArrayList<>();
    private OnItemClickListener listener;
    private List<TodoEntity> filterEntities=new ArrayList<>();
    private static final String TAG = "MyActivity";

    @NonNull
    @Override
    public AlltaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upcoming, parent, false);

        return new AlltaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlltaskHolder holder, int position) {
        TodoEntity currentTask = todoEntities.get(position);
        holder.textViewTitle.setText(currentTask.getTitle());

        if (currentTask.getPriority() == 1) {
            holder.textViewPriority.setText("Low");
            holder.textViewPriority.setTextColor(Color.rgb(0, 255, 0));
        } else if (currentTask.getPriority() == 2) {
            holder.textViewPriority.setText("Medium");
            holder.textViewPriority.setTextColor(Color.rgb(255, 251, 0));
        } else if (currentTask.getPriority() == 3) {
            holder.textViewPriority.setText("High");
            holder.textViewPriority.setTextColor(Color.rgb(255, 0, 0));
        } else {
            holder.textViewPriority.setText("Normal");

        }
        holder.textViewDescription.setText(currentTask.getDescription());

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String currentDateandTime = sdf.format(new Date());

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(currentTask.getUpdate_date());


        if(currentDateandTime.equals(strDate)){
            holder.textViewDate.setText("Today");
            holder.textViewPriority.setTextColor(Color.rgb(232, 255, 0));
        }else{
            holder.textViewDate.setText(strDate);
        }


//        String originalString = "2010-07-14 09:00:02";
//        Date date = null;
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(originalString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String newString = new SimpleDateFormat("H:mm").format(date);
//


    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "size of" + todoEntities.size());
        return todoEntities.size();

    }

    public void setTask(List<TodoEntity> todoEntities ) {
        List<TodoEntity> swap=new ArrayList<>(todoEntities);





        for(TodoEntity items: swap){
            Date currentDate=null;
            Date db_date=null;
            SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
            String currentdate=formatter1.format(new Date());
            String db_dateString=formatter1.format(items.getUpdate_date());
            try {
                currentDate=formatter1.parse(currentdate);
                db_date=formatter1.parse(db_dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if ( db_date.compareTo(currentDate) > 0){

                this.todoEntities.add(items);

            }
        }
        swap.clear();
        notifyDataSetChanged();
    }

    public TodoEntity gettaskAt(int position) {
        return todoEntities.get(position);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<TodoEntity> filteredList = new ArrayList<>();
           Log.i(TAG, "MyClass.getView() — get item number " + getItemCount());
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(filterEntities);
                System.out.println(charSequence);
            } else {
                Log.i(TAG, "MyClass.getView() — get item number " + getItemCount());
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (TodoEntity item : filterEntities) {

                     System.out.println(filteredList.size());



                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
           try {
               todoEntities.clear();
               todoEntities.addAll((List) filterResults.values);
               notifyDataSetChanged();
           }catch (Exception e){

           }

        }
    };

    class AlltaskHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;
        private TextView textViewDate;

        public AlltaskHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(todoEntities.get(position));
                    }

                }
            });


        }
    }

    public interface OnItemClickListener {
        void onItemClick(TodoEntity todoEntity);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }



}
