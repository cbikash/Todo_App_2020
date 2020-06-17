package np.com.cbikas.todoapp2020.ui.recycle_view_adapter;

import android.content.ClipData;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import np.com.cbikas.todoapp2020.R;
import np.com.cbikas.todoapp2020.data.TodoEntity;

public class AllTaskAdapter extends ListAdapter<TodoEntity, AllTaskAdapter.AlltaskHolder> implements Filterable {
    private OnItemClickListener listener;
    private List<TodoEntity> filterEntities;
    private static final String TAG = "MyActivity";

    public AllTaskAdapter() {

        super(DIFF_CALLBACK);
    }
    public static final DiffUtil.ItemCallback<TodoEntity> DIFF_CALLBACK=new DiffUtil.ItemCallback<TodoEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull TodoEntity oldItem, @NonNull TodoEntity newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull TodoEntity oldItem, @NonNull TodoEntity newItem) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            String strDate = sdf.format(oldItem.getUpdate_date());
            String strDate1 = sdf.format(newItem.getUpdate_date());

            String currentDateandTime = sdf.format(new Date());
            return oldItem.getTitle().equals(newItem.getTitle())&&
                    oldItem.getDescription().equals(newItem.getDescription())&&
                    oldItem.getPriority()==newItem.getPriority()&&
                    strDate1.equals(strDate);
        }
    };

    @NonNull
    @Override
    public AlltaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_task_item, parent, false);

        return new AlltaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlltaskHolder holder, int position) {
        TodoEntity currentTask = getItem(position);
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




    }


    public TodoEntity gettaskAt(int position) {
        return getItem(position);
    }



    @Override
    public  Filter getFilter() {
        return null;
    }

//    Filter filter=new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//            List<TodoEntity> filteredList = new ArrayList<>();
//            if (charSequence == null || charSequence.length() == 0) {
//                filteredList.addAll(filterEntities);
//                System.out.println(charSequence);
//            } else {
//
//                String filterPattern = charSequence.toString().toLowerCase().trim();
//
//                for (TodoEntity item : filterEntities) {
//
//                     System.out.println(filteredList.size());
//
//
//
//                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//           try {
//               todoEntities.clear();
//               todoEntities.addAll((List) filterResults.values);
//               notifyDataSetChanged();
//           }catch (Exception e){
//
//           }
//
//        }
//    };

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
                        listener.onItemClick(getItem(position));
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
