package com.example.todoapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    private Context context;
    private List<Task> mTask;
    private TaskItemListener mTaskItem;
    public TaskAdapter(Context context) {
        this.context = context;
        mTask = new ArrayList<>();
    }

    public void setClickListener(TaskItemListener mTaskItem){
        this.mTaskItem = mTaskItem;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new TaskViewHolder(view);
    }
    public  Task getItem(int position) {
        return mTask.get(position);
    }
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = mTask.get(position);
        if(task==null)
            return;
        holder.titletv.setText(task.getTitle());
        holder.desctv.setText(task.getDescription());
        holder.datetv.setText(task.getDate());
        holder.donecb.setChecked(task.isDone());
    }

    @Override
    public int getItemCount() {
        if(mTask!=null)
            return mTask.size();
        return 0;
    }
    public void add(Task t){
        mTask.add(t);
        notifyDataSetChanged();
    }
    public void update(int position, Task t){
        mTask.set(position, t);
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView titletv, desctv, datetv;
        private CheckBox donecb;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titletv = itemView.findViewById(R.id.title);
            desctv = itemView.findViewById(R.id.description);
            datetv = itemView.findViewById(R.id.date);
            donecb = itemView.findViewById(R.id.donecheckbox);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mTaskItem!=null){
                mTaskItem.onItemClick(view,getAdapterPosition());
            }
        }
    }

    public interface TaskItemListener {
        void onItemClick(View view, int position);
    }
}
