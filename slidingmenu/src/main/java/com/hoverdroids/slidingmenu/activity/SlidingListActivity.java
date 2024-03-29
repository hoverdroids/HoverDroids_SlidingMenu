package com.hoverdroids.slidingmenu.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import com.hoverdroids.slidingmenu.menu.SlidingMenu;

public class SlidingListActivity extends ListActivity implements SlidingActivityBase {

	private SlidingActivityHelper slidingActivityHelper;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		slidingActivityHelper = new SlidingActivityHelper(this);
		slidingActivityHelper.onCreate(savedInstanceState);
		ListView listView = new ListView(this);
		listView.setId(android.R.id.list);
		setContentView(listView);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */
	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		slidingActivityHelper.onPostCreate(savedInstanceState);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#findViewById(int)
	 */
	@Override
	public View findViewById(int id) {
		View v = super.findViewById(id);
		if (v != null)
			return v;
		return slidingActivityHelper.findViewById(id);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		slidingActivityHelper.onSaveInstanceState(outState);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#setContentView(int)
	 */
	@Override
	public void setContentView(int id) {
		setContentView(getLayoutInflater().inflate(id, null));
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#setContentView(android.view.View)
	 */
	@Override
	public void setContentView(View v) {
		setContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
	 */
	@Override
	public void setContentView(View v, LayoutParams params) {
		super.setContentView(v, params);
		slidingActivityHelper.registerAboveContentView(v, params);
	}

	/* (non-Javadoc)
	 * @see .lib.app.SlidingActivityBase#setBehindContentView(int)
	 */
	public void setBehindContentView(int id) {
		setBehindContentView(getLayoutInflater().inflate(id, null));
	}

	/* (non-Javadoc)
	 * @see .lib.app.SlidingActivityBase#setBehindContentView(android.view.View)
	 */
	public void setBehindContentView(View v) {
		setBehindContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	/* (non-Javadoc)
	 * @see .lib.app.SlidingActivityBase#setBehindContentView(android.view.View, android.view.ViewGroup.LayoutParams)
	 */
	public void setBehindContentView(View v, LayoutParams params) {
		slidingActivityHelper.setBehindContentView(v, params);
	}

	/* (non-Javadoc)
	 * @see .lib.app.SlidingActivityBase#getSlidingMenu()
	 */
	public SlidingMenu getSlidingMenu() {
		return slidingActivityHelper.getSlidingMenu();
	}

	/* (non-Javadoc)
	 * @see .lib.app.SlidingActivityBase#toggle()
	 */
	public void toggle() {
		slidingActivityHelper.toggle();
	}

	/* (non-Javadoc)
	 * @see .lib.app.SlidingActivityBase#showAbove()
	 */
	public void showContent() {
		slidingActivityHelper.showContent();
	}

	/* (non-Javadoc)
	 * @see .lib.app.SlidingActivityBase#showBehind()
	 */
	public void showMenu() {
		slidingActivityHelper.showMenu();
	}
	
	/*
	 * (non-Javadoc)
	 * @see .lib.app.SlidingActivityBase#showSecondaryMenu()
	 */
	public void showSecondaryMenu() {
		slidingActivityHelper.showSecondaryMenu();
	}

	/* (non-Javadoc)
	 * @see .lib.app.SlidingActivityBase#setSlidingActionBarEnabled(boolean)
	 */
	public void setSlidingActionBarEnabled(boolean b) {
		slidingActivityHelper.setSlidingActionBarEnabled(b);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onKeyUp(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		boolean b = slidingActivityHelper.onKeyUp(keyCode, event);
		if (b) return b;
		return super.onKeyUp(keyCode, event);
	}
}
