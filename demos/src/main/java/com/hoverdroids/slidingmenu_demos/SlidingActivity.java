package com.hoverdroids.slidingmenu_demos;

import android.os.Bundle;
import com.hoverdroids.slidingmenu.menu.SlidingMenu;

public class SlidingActivity extends com.hoverdroids.slidingmenu.activity.SlidingActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Title");

        initSlidingMenu();
    }

    private void initSlidingMenu(){
        //left
        setBehindContentView(R.layout.slidingmenu_right_frame);

        //Center/sliding menu
        setContentView(R.layout.slidingmenu_center_frame);

        final SlidingMenu menu = new SlidingMenu(this);
        //Left
        menu.setMenu(R.layout.slidingmenu_left_frame);
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //menu.setShadowWidthRes(R.dimen.shadow_width); //TODO CHRIS
        //menu.setShadowDrawable(R.drawable.shadow); //TODO CHRIS
        //menu.setBehindOffsetRes(R.dimen.slidingmenu_offset); //TODO CHRIS
        menu.setFadeDegree(0.35f); //TODO CHRIS


        //Right
        menu.setSecondaryMenu(R.layout.slidingmenu_right_frame);


        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);





    }
}
