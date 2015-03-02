package ca.ualberta.cs.team1travelexpenseapp;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;

public class ClaimList {
	private ArrayList<Claim> claimList;
	private ArrayList<Claim> displayedClaimList;
	private ArrayList<Tag> selectedTags;
	private ArrayList<Listener> listeners;
	
	public ArrayList<Claim> getClaims() {
		return claimList;
	}
	
	public void deleteClaim(int index) {
		claimList.remove(index);
	}
	
	public void addClaim(Claim claim) {
		claimList.add(claim);
	}
	
	public void updateClaim(Claim claim, int index){
		claimList.set(index, claim);
		for (Listener l : listeners) {
			l.update();
		}
	}
	
	public Claim getClaim(int index) {
		return claimList.get(index);
	}
	
	public void saveClaimList() {
		//to do 
	}
	
	public void filterByTags(ArrayList<Tag> tags) {
		//to-do
	}
	
	public void addListener(Listener listener) {
		//to do
	}
	
	private void notifyListeners() {
		//to do
	}
	
	public void onAddDRClick() {
		//to do
	}

	public void onAddClaimClick() {
		//to do
	}
	
	public void onManageTagsClick() {
		//to do
	}

	public void setClaimList() {
		claimList = new ArrayList<Claim>();
	}

	public void setDisplayedClaimList() {
		displayedClaimList = new ArrayList<Claim>();
	}

	public void setSelectedTags() {
		selectedTags = new ArrayList<Tag>();
		
	}

	public void setListeners() {
		listeners = new ArrayList<Listener>();
		
	}
	
}