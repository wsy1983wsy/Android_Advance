package com.wsy.event;

import com.wsy.event.listener.OnClickListener;
import com.wsy.event.listener.OnTouchListener;

public class Activity {
    public static void main(String[] args) {

        ViewGroup viewGroup1 = new ViewGroup(0, 0, 1000, 1000);
        viewGroup1.setName("顶级容器");

        ViewGroup viewGroup2 = new ViewGroup(0, 0, 500, 500);
        viewGroup2.setName("二级容器");

        View view = new View(0, 0, 200, 200);
        view.setName("View");

        viewGroup2.addView(view);
        viewGroup1.addView(viewGroup2);

        viewGroup1.setTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("顶级的OnTouch事件");
                return false;
            }
        });
        viewGroup2.setTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("第二级容器的OnTouch事件");
                return false;
            }
        });
        view.setClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("view的onClick事件");
            }
        });
        view.setTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("view的OnTouch事件");
                return false;
            }
        });


        //模拟事件分发
        MotionEvent motionEvent = new MotionEvent();
        motionEvent.setActionMasked(MotionEvent.ACTION_DOWN);
        //顶级容器分发
        viewGroup1.dispatchTouchEvent(motionEvent);
    }
}
