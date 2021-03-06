package ca.ualberta.cs.team1travelexpenseapp.test;

import java.util.Calendar;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import ca.ualberta.cs.team1travelexpenseapp.ClaimantClaimsListActivity;
import ca.ualberta.cs.team1travelexpenseapp.EditClaimActivity;
import ca.ualberta.cs.team1travelexpenseapp.R;

public class ClaimantClaimsListActivityTest extends ActivityInstrumentationTestCase2<ClaimantClaimsListActivity> {

	Activity activity;
	
	public ClaimantClaimsListActivityTest() {
		super(ClaimantClaimsListActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(true);
		activity = getActivity();
	}
	

	/**
	 * http://developer.android.com/training/activity-testing/activity-functional-testing.html
	 * 03/06/2015
	 * -test activity navigation
	 * -test US01.01.01
	 * -test US01.02.01
	 **/
	public void testAddClaimUI() {
		
		//change activity 
		final Button addClaimButton =
				(Button) activity.findViewById(R.id.addClaimButton);
		//monitor EditClaimActivity 
		ActivityMonitor receiverActivityMonitor = 
				getInstrumentation().addMonitor(EditClaimActivity.class.getName(),
						null, false);
		 
		
		// @------------!!!NOTE!!!-------------@
		// @!!! ensure emulator is unlocked !!!@
		// @------------!!!NOTE!!!-------------@
		TouchUtils.clickView(this, addClaimButton); //check that EditClaimActivity started
		
		
		EditClaimActivity receiverActivity = (EditClaimActivity) 
		        receiverActivityMonitor.waitForActivityWithTimeout(720); //time out in 12s
		assertNotNull("ReceiverActivity is null", receiverActivity);
		assertEquals("Monitor for ReceiverActivity has not been called",
		        1, receiverActivityMonitor.getHits());
		assertEquals("Activity is of wrong type",
				EditClaimActivity.class, receiverActivity.getClass());

		// Remove the ActivityMonitor
		//getInstrumentation().removeMonitor(receiverActivityMonitor);
		
		// add claim 
		activity = receiverActivity;
		
		//find addClaimButton
		final Button saveClaimButton =
				(Button) activity.findViewById(R.id.saveClaimButton);
		//find name 
		final EditText   name     = (EditText) activity.findViewById(R.id.claimNameBody);
		final EditText   dest     = (EditText) activity.findViewById(R.id.claimDestinationBody);
		final EditText   reason   = (EditText) activity.findViewById(R.id.claimReasonBody);
		final Button     addDest  = (Button)   activity.findViewById(R.id.addDestinationButton);
		final DatePicker fromDate = 
				(DatePicker) activity.findViewById(R.id.claimFromDate);
		final DatePicker endDate  = 
				(DatePicker) activity.findViewById(R.id.claimEndDate);
		
		// Send name
		getInstrumentation().runOnMainSync(new Runnable() {
		    @Override
		    public void run() {
		        name.requestFocus();
		    }
		});
		
		getInstrumentation().waitForIdleSync();
		getInstrumentation().sendStringSync("Cool Guy");
		getInstrumentation().waitForIdleSync();
		assertTrue("name text not set" , name.getText().toString().equals("Cool Guy"));
		
		// Send destination
		getInstrumentation().runOnMainSync(new Runnable() {
		    @Override
		    public void run() {
		        dest.requestFocus();
		    }
		});
		getInstrumentation().waitForIdleSync();
		getInstrumentation().sendStringSync("Cool dest");
		getInstrumentation().waitForIdleSync();
		assertTrue("destination text not set" , dest.getText().toString().equals("Cool dest"));
		
		
		// Send reason
		getInstrumentation().runOnMainSync(new Runnable() {
		    @Override
		    public void run() {
		        reason.requestFocus();
		    }
		});
		getInstrumentation().waitForIdleSync();
		getInstrumentation().sendStringSync("Cool reason");
		getInstrumentation().waitForIdleSync();
		assertTrue("reason text not set" , reason.getText().toString().equals("Cool reason"));
		
		
		// @------------!!!NOTE!!!-------------@
		// @!!! ensure emulator is unlocked !!!@
		// @------------!!!NOTE!!!-------------@
		TouchUtils.clickView(this, addDest); 
		
		Calendar beforeUIcalF = Calendar.getInstance();
		beforeUIcalF.set(fromDate.getYear(), fromDate.getMonth(), 
						fromDate.getDayOfMonth());
		Calendar beforeUIcalE = Calendar.getInstance();
		beforeUIcalE.set(endDate.getYear(), endDate.getMonth(), 
						endDate.getDayOfMonth());
		//set fromDate 
		// @------------!!!NOTE!!!-------------@
		// @!!! ensure emulator is unlocked !!!@
		// @------------!!!NOTE!!!-------------@
		TouchUtils.dragViewToBottom(this, receiverActivity, fromDate, 5); 
		TouchUtils.dragViewToBottom(this, receiverActivity, endDate, 5); 
		
		//check that user can change date of from and end 
		Calendar testDate = Calendar.getInstance();
		testDate.set(fromDate.getYear(), fromDate.getMonth(), 
				fromDate.getDayOfMonth());
		assertFalse("SAME" , beforeUIcalF.getTime().toString()
				.equals(testDate.getTime().toString()));
		testDate.set(endDate.getYear(), endDate.getMonth(), 
				endDate.getDayOfMonth());
		assertFalse("SAME" , beforeUIcalE.getTime().toString()
				.equals(testDate.getTime().toString()));
		
		getInstrumentation().removeMonitor(receiverActivityMonitor);
		//monitor ClaimClaimsListActivity
		receiverActivityMonitor = 
				getInstrumentation().addMonitor(ClaimantClaimsListActivity.class.getName(),
						null, false);
		 
		
		// @------------!!!NOTE!!!-------------@
		// @!!! ensure emulator is unlocked !!!@
		// @------------!!!NOTE!!!-------------@
		TouchUtils.clickView(this, saveClaimButton); //check that ClaimantClaimsListActivity started
		
		

		
		final View decorView = activity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView, saveClaimButton);


		
		ClaimantClaimsListActivity newReceiverActivity = (ClaimantClaimsListActivity) 
		receiverActivityMonitor.waitForActivityWithTimeout(720); //time out in 12s
		assertNotNull("ReceiverActivity is null", newReceiverActivity);
		assertEquals("Started new activity",
		        0, receiverActivityMonitor.getHits());
		assertEquals("Activity is of wrong type",
				ClaimantClaimsListActivity.class, newReceiverActivity.getClass());

		// Remove the ActivityMonitor
		getInstrumentation().removeMonitor(receiverActivityMonitor);
		
		
		
	}
	
	
}
