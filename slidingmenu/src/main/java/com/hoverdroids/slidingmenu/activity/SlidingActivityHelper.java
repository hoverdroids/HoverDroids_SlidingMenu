package com.hoverdroids.slidingmenu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.hoverdroids.slidingmenu.menu.SlidingMenu;
import com.hoverdroids.slidingmenu.R;

public class SlidingActivityHelper {

	private Activity activity;

	private SlidingMenu slidingMenu;

	private View viewAbove;

	private View viewBehind;

	private boolean isBroadcasting = false;

	private boolean onPostCreateCalled = false;

	private boolean enableSlide = true;

	/**
	 * Instantiates a new SlidingActivityHelper.
	 *
	 * @param activity the associated activity
	 */
	public SlidingActivityHelper(Activity activity) {
		this.activity = activity;
	}

	/**
	 * Sets slidingMenu as a newly inflated SlidingMenu. Should be called within the activitiy's onCreate()
	 *
	 * @param savedInstanceState the saved instance state (unused)
	 */
	public void onCreate(Bundle savedInstanceState) {
		slidingMenu = (SlidingMenu) LayoutInflater.from(activity).inflate(R.layout.slidingmenu, null);
	}

	/**
	 * Further SlidingMenu initialization. Should be called within the activitiy's onPostCreate()
	 *
	 * @param savedInstanceState the saved instance state (unused)
	 */
	public void onPostCreate(Bundle savedInstanceState) {
		if (viewBehind == null || viewAbove == null) {
			throw new IllegalStateException("Both setBehindContentView must be called " +
					"in onCreate in addition to setContentView.");
		}

		onPostCreateCalled = true;

		slidingMenu.attachToActivity(activity, 
				enableSlide ? SlidingMenu.SLIDING_WINDOW : SlidingMenu.SLIDING_CONTENT);
		
		final boolean open;
		final boolean secondary;
		if (savedInstanceState != null) {
			open = savedInstanceState.getBoolean("SlidingActivityHelper.open");
			secondary = savedInstanceState.getBoolean("SlidingActivityHelper.secondary");
		} else {
			open = false;
			secondary = false;
		}
		new Handler().post(new Runnable() {
			public void run() {
				if (open) {
					if (secondary) {
						slidingMenu.showSecondaryMenu(false);
					} else {
						slidingMenu.showMenu(false);
					}
				} else {
					slidingMenu.showContent(false);					
				}
			}
		});
	}

	/**
	 * Controls whether the ActionBar slides along with the above view when the menu is opened,
	 * or if it stays in place.
	 *
	 * @param slidingActionBarEnabled True if you want the ActionBar to slide along with the SlidingMenu,
	 * false if you want the ActionBar to stay in place
	 */
	public void setSlidingActionBarEnabled(boolean slidingActionBarEnabled) {
		if (onPostCreateCalled) {
			throw new IllegalStateException("enableSlidingActionBar must be called in onCreate.");
		}
		enableSlide = slidingActionBarEnabled;
	}

	/**
	 * Finds a view that was identified by the id attribute from the XML that was processed in onCreate(Bundle).
	 * 
	 * @param id the resource id of the desired view
	 * @return The view if found or null otherwise.
	 */
	public View findViewById(int id) {
		View v;
		if (slidingMenu != null) {
			v = slidingMenu.findViewById(id);
			if (v != null)
				return v;
		}
		return null;
	}

	/**
	 * Called to retrieve per-instance state from an activity before being killed so that the state can be
	 * restored in onCreate(Bundle) or onRestoreInstanceState(Bundle) (the Bundle populated by this method
	 * will be passed to both). 
	 *
	 * @param outState Bundle in which to place your saved state.
	 */
	public void onSaveInstanceState(Bundle outState) {
		outState.putBoolean("SlidingActivityHelper.open", slidingMenu.isMenuShowing());
		outState.putBoolean("SlidingActivityHelper.secondary", slidingMenu.isSecondaryMenuShowing());
	}

	/**
	 * Register the above content view.
	 *
	 * @param v the above content view to register
	 * @param params LayoutParams for that view (unused)
	 */
	public void registerAboveContentView(View v, LayoutParams params) {
		if (!isBroadcasting) {
			viewAbove = v;
		}
	}

	/**
	 * Set the activity content to an explicit view. This view is placed directly into the activity's view
	 * hierarchy. It can itself be a complex view hierarchy. When calling this method, the layout parameters
	 * of the specified view are ignored. Both the width and the height of the view are set by default to
	 * MATCH_PARENT. To use your own layout parameters, invoke setContentView(android.view.View,
	 * android.view.ViewGroup.LayoutParams) instead.
	 *
	 * @param v The desired content to display.
	 */
	public void setContentView(View v) {
		isBroadcasting = true;
		activity.setContentView(v);
	}

	/**
	 * Set the behind view content to an explicit view. This view is placed directly into the behind view 's view hierarchy.
	 * It can itself be a complex view hierarchy.
	 *
	 * @param view The desired content to display.
	 * @param layoutParams Layout parameters for the view. (unused)
	 */
	public void setBehindContentView(View view, LayoutParams layoutParams) {
		viewBehind = view;
		slidingMenu.setMenu(viewBehind);
	}

	/**
	 * Gets the SlidingMenu associated with this activity.
	 *
	 * @return the SlidingMenu associated with this activity.
	 */
	public SlidingMenu getSlidingMenu() {
		return slidingMenu;
	}

	/**
	 * Toggle the SlidingMenu. If it is open, it will be closed, and vice versa.
	 */
	public void toggle() {
		slidingMenu.toggle();
	}

	/**
	 * Close the SlidingMenu and show the content view.
	 */
	public void showContent() {
		slidingMenu.showContent();
	}

	/**
	 * Open the SlidingMenu and show the menu view.
	 */
	public void showMenu() {
		slidingMenu.showMenu();
	}

	/**
	 * Open the SlidingMenu and show the secondary menu view. Will default to the regular menu
	 * if there is only one.
	 */
	public void showSecondaryMenu() {
		slidingMenu.showSecondaryMenu();
	}

	/**
	 * On key up.
	 *
	 * @param keyCode the key code
	 * @param event the event
	 * @return true, if successful
	 */
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && slidingMenu.isMenuShowing()) {
			showContent();
			return true;
		}
		return false;
	}
}
