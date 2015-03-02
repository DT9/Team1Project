package ca.ualberta.cs.team1travelexpenseapp;

import java.util.ArrayList;

public class TagList {
	private ArrayList<Tag> tagList;
	private ArrayList<Listener> listeners;
	
	public void saveTagList(){
		
	}
	
	public void addListener(Listener listener){
		listeners.add(listener);
	}
	
	public void removeListener(Listener listener){
		listeners.remove(listener);
	}
	
	private void notifyListeners(){
		for(int i=0; i<listeners.size();i++){
			listeners.get(i).update();
		}
	}

	public ArrayList<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList<Tag> tagList) {
		this.tagList = tagList;
	}
}
