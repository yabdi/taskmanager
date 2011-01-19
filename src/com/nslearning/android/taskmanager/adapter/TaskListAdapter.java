package com.nslearning.android.taskmanager.adapter;

import java.util.ArrayList;


import com.nslearning.android.taskmanager.R;
import com.nslearning.android.taskmanager.tasks.Task;
import com.nslearning.android.taskmanager.views.TaskListItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TaskListAdapter extends BaseAdapter {
	
	private ArrayList<Task> tasks;
	private Context context; // 
	
	
	public TaskListAdapter(ArrayList<Task> tasks, Context context) {
		super();
		this.tasks = tasks;
		this.context = context;
	}

	public int getCount() {
		return tasks.size();
	}

	public Task getItem(int position) {
		return (null == tasks) ? null : tasks.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TaskListItem tli;
		if(null == convertView) {
			tli = (TaskListItem)View.inflate(context, R.layout.task_list_item, null);
		} else {
			tli = (TaskListItem)convertView;
		}
		tli.setTask(tasks.get(position));
		return tli;
	}

	public void forceReload() {
		notifyDataSetChanged();
		
	}

	public void toggleTaskCompleteAtPosition(int position) {
		Task t = tasks.get(position);
		t.toggleComplete();
		notifyDataSetChanged();
	}

	public Long[] RemoveCompletedTasks() {
		ArrayList<Long> completedIds = new ArrayList<Long>();
		ArrayList<Task> completedTasks = new ArrayList<Task>();
		for(Task task : tasks) {
			if(task.isComplete()) {
				completedIds.add(task.getId());
				completedTasks.add(task);
			}
		}
		tasks.removeAll(completedTasks);
		notifyDataSetChanged();
		
		return completedIds.toArray(new Long[]{});
		
	}

}
