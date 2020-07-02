package com.wsy.view_day01.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.customview.widget.ViewDragHelper;

public class VerticalDragListView extends FrameLayout {
    private ViewDragHelper dragHelper;
    private ListView draggedListView;
    private int menuHeight;

    public VerticalDragListView(Context context) {
        this(context, null);
    }

    public VerticalDragListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalDragListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        dragHelper = ViewDragHelper.create(this, mDragHelperCallback);
    }

    ViewDragHelper.Callback mDragHelperCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return child == draggedListView;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            if (top <= 0) {
                top = 0;
            }
            if (top > menuHeight) {
                top = menuHeight;
            }
            return top;
        }

        // 2.4 手指松开的时候两者选其一，要么打开要么关闭
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            // Log.e("TAG", "yvel -> " + yvel + " mMenuHeight -> " + mMenuHeight);
            // Log.e("TAG", "top -> " + mDragListView.getTop());
            if (releasedChild == draggedListView) {
                if (draggedListView.getTop() > menuHeight / 2) {
                    // 滚动到菜单的高度（打开）
                    dragHelper.settleCapturedViewAt(0, menuHeight);
                    mMenuIsOpen = true;
                } else {
                    // 滚动到0的位置（关闭）
                    dragHelper.settleCapturedViewAt(0, 0);
                    mMenuIsOpen = false;
                }
                invalidate();
            }
        }
    };

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            View menuView = getChildAt(0);
            menuHeight = menuView.getMeasuredHeight();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount != 2) {
            throw new RuntimeException("VerticalDragListView 只能包含两个子布局");
        }
        draggedListView = (ListView) getChildAt(1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    // 现象就是ListView可以滑动，但是菜单滑动没有效果了
    private float mDownY;
    private boolean mMenuIsOpen;

    // ecause ACTION_DOWN was not received for this pointer before ACTION_MOVE
    // VDLV.onInterceptTouchEvent().DOWN -> LV.onTouch() ->
    // VDLV.onInterceptTouchEvent().MOVE -> VDLV.onTouchEvent().MOVE

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 菜单打开要拦截
        if (mMenuIsOpen) {
            return true;
        }

        // 向下滑动拦截，不要给ListView做处理
        // 谁拦截谁 父View拦截子View ，但是子 View 可以调这个方法
        // requestDisallowInterceptTouchEvent 请求父View不要拦截，改变的其实就是 mGroupFlags 的值
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                // 让 DragHelper 拿一个完整的事件
                dragHelper.processTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
                if ((moveY - mDownY) > 0 && !canChildScrollUp()) {
                    // 向下滑动 && 滚动到了顶部，拦截不让ListView做处理
                    return true;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    /**
     * @return Whether it is possible for the child view of this layout to
     * scroll up. Override this if the child view is a custom view.
     * 判断View是否滚动到了最顶部,还能不能向上滚
     */
    public boolean canChildScrollUp() {
        return draggedListView.canScrollHorizontally(-1);
    }

    /**
     * 响应滚动
     */
    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}
