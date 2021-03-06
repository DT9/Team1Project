package ca.ualberta.cs.team1travelexpenseapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ApproverClaimInfo extends Activity {
	TextView info;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_claim_info);
		info = (TextView) findViewById(R.id.approverClaimInfoTextView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approver_claim_info, menu);
		return true;
	}

	public void onStart() {
		super.onStart();
		info.setText(ClaimListController.getCurrentClaim().toString());
	}
}
