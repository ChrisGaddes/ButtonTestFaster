package com.chrisgaddes.buttontestfaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Button test;
    private PopupWindow popupWindow;
    //private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;


    // layoutInflator allows loading a new layout inside our popped window
    private LayoutInflater layoutInflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = (Button) findViewById(R.id.button);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.test, null);

                popupWindow = new PopupWindow(container, 400, 400, true);
                popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, 500, 500);

                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
    }
}
