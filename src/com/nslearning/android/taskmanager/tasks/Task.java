package com.nslearning.android.taskmanager.tasks;

public class Task {

	private String name;
	private boolean complete;
	private long  id;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public Task(String taskName){
		name = taskName;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public void toggleComplete() {
		complete = !complete;		
	}


		
		
}
