package com.example.taskmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    final ArrayList<Task> taskList;
    public TaskAdapter(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.titleview.setText(task.getTitle());
        holder.descview.setText(task.getDescription());
        holder.dateview.setText(task.getDate());
        holder.delb.setOnClickListener(v -> {
            taskList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, taskList.size());
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView titleview, descview, dateview;
        CheckBox check;
        Button delb;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleview = itemView.findViewById(R.id.titleview);
            descview = itemView.findViewById(R.id.descview);
            dateview = itemView.findViewById(R.id.dateview);
            delb = itemView.findViewById(R.id.delb);
        }
    }
}
