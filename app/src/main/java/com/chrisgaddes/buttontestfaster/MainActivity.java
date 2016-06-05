package com.chrisgaddes.buttontestfaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Button btn_test;
    private PopupWindow popupWindow;
    //private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;


    // layoutInflator allows loading a new layout inside our popped window
    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_test = (Button) findViewById(R.id.button);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        // listens for touch

        btn_test.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // gets center coordinates of button and matches it to center of popup
                int width_popup = 500; // width of popup
                int height_popup = 500; // height of popup
                int pos[] = new int[2];
                btn_test.getLocationOnScreen(pos); // get location of pressed button
                int x1 = pos[0], y1 = pos[1];
                int loc_popup_x = x1 + btn_test.getWidth() / 2 - width_popup / 2;
                int loc_popup_y = y1 + btn_test.getHeight() / 2 - height_popup / 2;

                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.activity_popup_buttons, null);

                popupWindow = new PopupWindow(container, width_popup, height_popup, true);
                popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, loc_popup_x, loc_popup_y);

                popupWindow.setFocusable(false);
                popupWindow.setOutsideTouchable(true);

                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
                return false;
            }
        });
    }
}
