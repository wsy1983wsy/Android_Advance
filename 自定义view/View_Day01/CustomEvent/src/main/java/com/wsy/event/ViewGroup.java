package com.wsy.event;

import java.util.ArrayList;
import java.util.List;

public class ViewGroup extends View {
    List<View> childList = new ArrayList<>();
    private View[] children = new View[0];

    public ViewGroup(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    public void addView(View view) {
        if (view == null) {
            return;
        }
        childList.add(view);
        children = (View[]) childList.toArray(new View[childList.size()]);
    }

    //事件分发接口
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println(getName() + " dispatchTouchEvent");
        boolean intercepted = onInterceptTouchEvent(ev);
        int actionMasked = ev.getActionMasked();
        TouchTarget newTouchTarget;
        boolean handled = false;

        if (actionMasked != MotionEvent.ACTION_CANCEL && !intercepted) {
            //DOWN事件
            if (actionMasked == MotionEvent.ACTION_DOWN) {
                final View[] subChildren = children;
                //耗时， 命中目标的概率大一些
                for (int i = subChildren.length - 1; i >= 0; i--) {
                    View child = subChildren[i];
                    if (!child.isContainer(ev.getX(), ev.getY())) {
                        continue;
                    }
                    //能够接受事件 把事件分发给child
                    if (dispatchTransformedTouchEvent(ev, child)) {
                        // view[] 采取了Message的方式，链表结构
                        handled = true;
                        newTouchTarget = addTouchTarget(child);
                        break;
                    }
                }
            }
        }
        //没有人接受该事件,当前view处理事件
        if (firstTouchTarget == null) {
            handled = dispatchTransformedTouchEvent(ev, null);
        }
        return handled;
    }

    private boolean dispatchTransformedTouchEvent(MotionEvent event, View child) {
        boolean handled = false;
        if (child != null) {
            handled = child.dispatchTouchEvent(event);
        } else {
            handled = super.dispatchTouchEvent(event);
        }
        return handled;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    private TouchTarget firstTouchTarget = null;

    private TouchTarget addTouchTarget(View child) {
        final TouchTarget target = TouchTarget.obtain(child);
        target.next = firstTouchTarget;
        firstTouchTarget = target;
        return target;
    }

    private static final class TouchTarget {
        public View child;
        public TouchTarget next;
        //回收池
        private static TouchTarget recycleBin;
        private static final Object recycleLock = new Object[0];
        private static int recycledCount;

        public static TouchTarget obtain(View child) {
            TouchTarget target;
            synchronized (recycleLock) {
                if (recycleBin == null) {
                    target = new TouchTarget();
                } else {
                    target = recycleBin;
                }
                recycleBin = target.next;
                recycledCount--;
                target.next = null;
            }
            target.child = child;
            return target;
        }

        /**
         * 回收
         */
        public void recycle() {
            if (child == null) {
                throw new IllegalStateException("已经被回收过了");
            }
            synchronized (recycleLock) {
                if (recycledCount < 32) {
                    next = recycleBin;
                    recycleBin = this;
                    recycledCount++;
                }
            }
        }
    }
}
