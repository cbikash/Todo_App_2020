package np.com.cbikas.todoapp2020.ui.recycle_view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import np.com.cbikas.todoapp2020.R;
import np.com.cbikas.todoapp2020.data.TodoEntity;

public class AllTaskAdapter extends RecyclerView.Adapter<AllTaskAdapter.AlltaskHolder> {
    private List<TodoEntity> todoEntities=new ArrayList<>();


    @NonNull
    @Override
    public AlltaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_task_item, parent, false);

        return new AlltaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlltaskHolder holder, int position) {
        TodoEntity currentTask=todoEntities.get(position);
        holder.textViewTitle.setText(currentTask.getTitle());

        if(currentTask.getPriority() == 1){
            holder.textViewPriority.setText("Low");
            holder.textViewPriority.setTextColor(Color.rgb(0, 255, 0));
        }else if(currentTask.getPriority() == 2){
            holder.textViewPriority.setText("Medium");
            holder.textViewPriority.setTextColor(Color.rgb(255, 251, 0));
        }else if(currentTask.getPriority() == 3){
            holder.textViewPriority.setText("High");
            holder.textViewPriority.setTextColor(Color.rgb(255, 0, 0));
        }else{
            holder.textViewPriority.setText("Normal");

        }
        holder.textViewDescription.setText(currentTask.getDescription());
        holder.textViewDate.setText(String.valueOf(currentTask.getUpdate_date()));
    }

    @Override
    public int getItemCount() {
        return todoEntities.size();
    }
    public void setTask(List<TodoEntity> todoEntities){
        this.todoEntities=todoEntities;
        notifyDataSetChanged();
    }

    class AlltaskHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;
        private TextView textViewDate;

        public AlltaskHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.text_view_title);
            textViewDescription=itemView.findViewById(R.id.text_view_description);
            textViewPriority =itemView.findViewById(R.id.text_view_priority);
            textViewDate=itemView.findViewById(R.id.text_view_date);



        }
    }
}
